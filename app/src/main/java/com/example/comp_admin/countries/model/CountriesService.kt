package com.example.comp_admin.countries.model

import com.example.comp_admin.countries.di.DaggerApiComponent
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Inject

class CountriesService {
    @Inject
    lateinit var api : CountriesApi

    init {
       //Moving our service to dagger class
       /* api = Retrofit.Builder()*/
     DaggerApiComponent.create().inject(this)

    }
    //Single is basically an observable that only omits one value and then closes.
    fun getCountries(): Single<List<Country>>{
        return api.getCountries()
    }
}