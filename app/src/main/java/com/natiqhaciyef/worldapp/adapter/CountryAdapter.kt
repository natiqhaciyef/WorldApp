package com.natiqhaciyef.worldapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.natiqhaciyef.worldapp.databinding.RecyclerItemRowBinding
import com.natiqhaciyef.worldapp.model.CountryModel
import com.squareup.picasso.Picasso

class CountryAdapter(val list: ArrayList<CountryModel>) : RecyclerView.Adapter<CountryAdapter.CountryHolder>() {

    class CountryHolder(var binding: RecyclerItemRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val binding = RecyclerItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CountryHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.binding.itemCountryNameText.text = list[position].name
        holder.binding.itemCapitalText.text = list[position].capital
        Picasso.get().load(list[position].flag).into(holder.binding.itemRowImage)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateCountryList(countryList : ArrayList<CountryModel>){
        list.clear()
        list.addAll(countryList)
        notifyDataSetChanged()
    }
}