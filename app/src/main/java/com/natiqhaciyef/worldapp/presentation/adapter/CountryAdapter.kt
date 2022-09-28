package com.natiqhaciyef.worldapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.natiqhaciyef.worldapp.R
import com.natiqhaciyef.worldapp.databinding.RecyclerItemRowBinding
import com.natiqhaciyef.worldapp.data.model.CountryModel


class CountryAdapter(val list: ArrayList<CountryModel>) : RecyclerView.Adapter<CountryAdapter.CountryHolder>() {

    var listener : OnClickService? = null
    class CountryHolder(var binding: RecyclerItemRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val binding = DataBindingUtil.inflate<RecyclerItemRowBinding>(LayoutInflater.from(parent.context), R.layout.recycler_item_row ,parent,false)
        return CountryHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.binding.country = list[position]
        holder.itemView.setOnClickListener {
            listener?.onItemClick(list[position].id)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateCountryList(countryList : List<CountryModel>){
        list.clear()
        list.addAll(countryList)
        notifyDataSetChanged()
    }

     fun onCountryClicked(listener: OnClickService) {
        this.listener = listener
    }
}