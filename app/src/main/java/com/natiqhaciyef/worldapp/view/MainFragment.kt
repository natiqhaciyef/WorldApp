package com.natiqhaciyef.worldapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.natiqhaciyef.worldapp.R
import com.natiqhaciyef.worldapp.adapter.CountryAdapter
import com.natiqhaciyef.worldapp.adapter.OnClickService
import com.natiqhaciyef.worldapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {
    private lateinit var viewModel : MainViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this@MainFragment)[MainViewModel::class.java]
        viewModel.refreshCountry()

        mainFragmentRecyclerView.layoutManager = LinearLayoutManager(context)
        mainFragmentRecyclerView.adapter = countryAdapter
        countryAdapter.onCountryClicked(object: OnClickService{
            override fun onItemClick(id: Int) {
                val action = MainFragmentDirections.actionMainFragmentToDetailsFragment(id)
                Navigation.findNavController(requireView()).navigate(action)
            }
        })

        swipeRefreshLayout.setOnRefreshListener {
            mainFragmentRecyclerView.visibility = View.GONE
            mainFragmentProgressBar.visibility = View.VISIBLE
            swipeRefreshLayout.isRefreshing = false
            viewModel.refreshFromApi()
        }

        liveDataObserve()
    }

    private fun liveDataObserve(){
        viewModel.countries.observe(viewLifecycleOwner, Observer { countries ->
            countries?.let {
                mainFragmentRecyclerView.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }
        })

        viewModel.countryLoad.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    mainFragmentProgressBar.visibility = View.VISIBLE
                    mainFragmentRecyclerView.visibility = View.GONE
                }else{
                    mainFragmentProgressBar.visibility = View.GONE
                }
            }
        })
    }
}