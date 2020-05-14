package com.example.comp_admin.countries.di

import com.example.comp_admin.countries.model.CountriesService
import com.example.comp_admin.countries.viewmodel.ListViewModel
import dagger.Component

@Component (modules = [ApiModule::class])
interface ApiComponent {

    //this function will help dagger inject the right components
    // from ApiModule into CountriesService.
    fun inject(service: CountriesService)
    //this function will help dagger inject the right components
    // from ApiModule into ListViewModel.
    fun inject(viewModel: ListViewModel)
}