package com.natiqhaciyef.worldapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.natiqhaciyef.worldapp.model.CountryModel
import com.natiqhaciyef.worldapp.roomdb.CountryDatabase
import kotlinx.coroutines.launch

class DetailsViewModel(application: Application) : CoroutinesViewModel(application) {
    val country = MutableLiveData<CountryModel>()

    fun getDataFromRoom(id: Int) {
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            country.value = dao.getCountryByID(id)
        }
    }
}