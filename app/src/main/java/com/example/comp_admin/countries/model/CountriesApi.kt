package com.example.comp_admin.countries.model

import io.reactivex.Single
import retrofit2.http.GET

interface CountriesApi {
    @GET("DevTides/countries/master/countriesV2.json")
    fun getCountries():Single<List<Country>>
}