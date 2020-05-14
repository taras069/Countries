package com.example.comp_admin.countries.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.comp_admin.countries.R
import com.example.comp_admin.countries.model.Country
import com.example.comp_admin.countries.util.getProgressDrawable
import com.example.comp_admin.countries.util.loadImage
import kotlinx.android.synthetic.main.item_country.view.*

class ListCountryAdapter(var mList: ArrayList<Country>) :
    RecyclerView.Adapter<ListCountryAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_country, parent, false)
    )
    override fun getItemCount(): Int = mList.size
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mList[position])
    }
    fun updateCountries(list :List<Country>){
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var imageView = itemView.imageView
        private var progressDrawable = getProgressDrawable(itemView.context)

        fun bind(country: Country) {
            itemView.tv_country_name.text = country.countryName
            itemView.tv_capital.text = country.capital
            imageView.loadImage(country.flag, progressDrawable)
        }
    }

}