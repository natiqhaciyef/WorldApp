package com.natiqhaciyef.worldapp.network.service

import com.natiqhaciyef.worldapp.data.model.CountryModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface CountryAPI {
    //natiqhaciyef/Country-JSON/main/Country%20API%20kit/Country%20API.json

    @GET("natiqhaciyef/Country-JSON/4d0f9b262d512fde7bb1c0c4d826065cedd19764/Country%20API%20kit/Country%20API.json")
    fun getData(): Single<ArrayList<CountryModel>>

    //Single -> bir defe APIye muraciet edir ve mueyyen muddete qeder API ile elaqeni kesir
    //Observable -> API ile daimi elaqe saxlayir ve her hansi  deyisiklik olduqda ani olaraq o deyisikliyi update edir
}