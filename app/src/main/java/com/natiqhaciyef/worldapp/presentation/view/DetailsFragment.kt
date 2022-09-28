package com.natiqhaciyef.worldapp.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.natiqhaciyef.worldapp.R
import com.natiqhaciyef.worldapp.network.util.downloadFromUrl
import com.natiqhaciyef.worldapp.network.util.placeHolder
import com.natiqhaciyef.worldapp.presentation.viewmodel.DetailsViewModel
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {
    private lateinit var viewModel: DetailsViewModel
    private var countryId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            countryId = DetailsFragmentArgs.fromBundle(it).id
        }

        viewModel = ViewModelProvider(this@DetailsFragment)[DetailsViewModel::class.java]
        viewModel.getDataFromRoom(countryId)

        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.country.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
                countryNameText.text = "${country.name}"
                capitalText.text = "Capital: ${country.capital}"
                regionText.text = "Region: ${country.region}"
                currencyText.text = "Currency: ${country.currency}"
                religionText.text = "Religion: ${country.religion}"
                languageText.text = "Language: ${country.language}"
                context?.let {
                    flagImage.downloadFromUrl(country.flagUrl, placeHolder(it))
                }
            }
        })
    }
}