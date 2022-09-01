package com.natiqhaciyef.worldapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.natiqhaciyef.worldapp.model.CountryModel

class MainViewModel : ViewModel() {
    val countries = MutableLiveData<ArrayList<CountryModel>>()
    val countryLoad = MutableLiveData<Boolean>()

    fun refreshCountry(){
        val country1 = CountryModel("Azerbaijan","Baku","Asia","AZN","Azerbaijani","https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/aze.png")
        val country2 = CountryModel("Turkey","Ankara","Asia","TR","Turkish","https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/tur.png")
        val country3 = CountryModel("Canada","Ottawa","Americas","CAD","English","https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/can.png")

        val countryList = arrayListOf(country1, country2, country3)
        countries.value = countryList
        countryLoad.value = false
    }
}