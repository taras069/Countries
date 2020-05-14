package com.example.comp_admin.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.comp_admin.countries.di.DaggerApiComponent
import com.example.comp_admin.countries.model.CountriesService
import com.example.comp_admin.countries.model.Country
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel : ViewModel() {

    @Inject
    lateinit var countriesService : CountriesService //= CountriesService()
    init {

        DaggerApiComponent.create().inject(this)

    }

    private val disposable = CompositeDisposable()

    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true
        disposable.add(
            //we called the country's service getCountries
            //This simply returns a single list country.
            countriesService.getCountries()
                //So in order to avoid processing on main thread we subscribe our observer to background thread
                .subscribeOn(Schedulers.newThread())
                    // get all data (result)  on main thread
                .observeOn(AndroidSchedulers.mainThread())
                    //define next actions after we receive the data
                .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(value: List<Country>?) {
                        countries.value = value
                        countryLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable?) {
                       countryLoadError.value = true
                        loading.value = false
                    }

                })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}