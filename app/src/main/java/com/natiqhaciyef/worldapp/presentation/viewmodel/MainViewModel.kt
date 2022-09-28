package com.natiqhaciyef.worldapp.presentation.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.natiqhaciyef.worldapp.data.model.CountryModel
import com.natiqhaciyef.worldapp.data.roomdb.CountryDatabase
import com.natiqhaciyef.worldapp.network.service.CountryService
import com.natiqhaciyef.worldapp.network.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : CoroutinesViewModel(application) {
    val context = getApplication<Application>().applicationContext
    val countries = MutableLiveData<List<CountryModel>>()
    val countryLoad = MutableLiveData<Boolean>()
    val service = CountryService()
    private val disposable = CompositeDisposable()
    private var customSharedPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L

    fun refreshCountry(){
        val timer = customSharedPreferences.getTime()
        if (timer != null && timer != 0L && System.nanoTime() - timer < refreshTime){
            getDataFromRoom()
        }else{
            getDataFromAPI()
        }
    }

    fun refreshFromApi(){
        getDataFromAPI()
    }

    private fun getDataFromRoom(){
        countryLoad.value = true
        launch {
            val countries = CountryDatabase(getApplication()).countryDao().getAllCountries()
            showCountries(countries)
//            Toast.makeText(getApplication(),"Data from Room", Toast.LENGTH_LONG).show()
        }
    }

    private fun getDataFromAPI(){
        countryLoad.value = true
        disposable.add(service.getCountry()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<ArrayList<CountryModel>>(){
                    override fun onSuccess(t: ArrayList<CountryModel>) {
                        storeInRoom(t)
//                        Toast.makeText(getApplication(),"Data from API", Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(context, e.localizedMessage, Toast.LENGTH_LONG).show()
                    }
                })
        )
    }

    private fun showCountries(countryList: List<CountryModel>){
        countries.value = countryList
        countryLoad.value = false
    }

    private fun storeInRoom(list: List<CountryModel>){
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            dao.deleteAllCountries()
            val keys = dao.insertAllCountries(*list.toTypedArray())    // list-i tek-tek elementler halinda ayiraraq prosesi icra edir
            var i = 0
            while (i< list.size){
                list[i].id = keys[i].toInt()
                i++
            }
            showCountries(list)
        }

        customSharedPreferences.saveTime(System.nanoTime())
    }
}