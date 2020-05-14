package com.example.comp_admin.countries.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.comp_admin.countries.R
import com.example.comp_admin.countries.adapters.ListCountryAdapter
import com.example.comp_admin.countries.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mAdapter = ListCountryAdapter(arrayListOf())
    lateinit var viewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        rv_countries.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context)
        }

        swipe_layout.setOnRefreshListener {
            swipe_layout.isRefreshing = false
            viewModel.refresh()
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.countries.observe(this, Observer { countries ->
            rv_countries.visibility = View.VISIBLE
            countries?.let { mAdapter.updateCountries(countries) }
        })
        viewModel.countryLoadError.observe(this, Observer { isError ->
            isError.let { tv_error.visibility = if (it) View.VISIBLE else View.GONE
                Log.d("response2", it.toString() )}
        })
        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading.let {
                progress_indicator.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    tv_error.visibility = View.GONE
                     rv_countries.visibility = View.GONE
                    Log.d("response1", it.toString())
                }
            }
        })
    }
}
