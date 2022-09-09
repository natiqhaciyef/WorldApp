package com.natiqhaciyef.worldapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.natiqhaciyef.worldapp.databinding.RecyclerItemRowBinding
import com.natiqhaciyef.worldapp.model.CountryModel
import com.natiqhaciyef.worldapp.util.downloadFromUrl
import com.natiqhaciyef.worldapp.util.placeHolder


class CountryAdapter(val list: ArrayList<CountryModel>) : RecyclerView.Adapter<CountryAdapter.CountryHolder>() {

    var listener : OnClickService? = null
    class CountryHolder(var binding: RecyclerItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val binding = RecyclerItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CountryHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.binding.itemCountryNameText.text = list[position].name
        holder.binding.itemCapitalText.text = list[position].capital
        holder.binding.itemRowImage.downloadFromUrl(list[position].flagUrl, placeHolder(holder.itemView.context))
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