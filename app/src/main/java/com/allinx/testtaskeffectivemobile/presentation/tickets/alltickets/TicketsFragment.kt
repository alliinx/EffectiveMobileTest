package com.allinx.testtaskeffectivemobile.presentation.tickets.alltickets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.allinx.domain.models.ticket.Tickets
import com.allinx.testtaskeffectivemobile.Constants.EmptyString.EMPTY_STRING
import com.allinx.testtaskeffectivemobile.Constants.OffersTickets.DATE
import com.allinx.testtaskeffectivemobile.Constants.OffersTickets.PASSENGERS
import com.allinx.testtaskeffectivemobile.Constants.SearchBottomSheetFragment.CITY_FROM
import com.allinx.testtaskeffectivemobile.Constants.SearchBottomSheetFragment.CITY_TO
import com.allinx.testtaskeffectivemobile.R
import com.allinx.testtaskeffectivemobile.databinding.FragmentTicketsBinding
import com.allinx.testtaskeffectivemobile.extension.hideLoading
import com.allinx.testtaskeffectivemobile.extension.showLoading
import com.allinx.testtaskeffectivemobile.presentation.tickets.alltickets.adapter.TicketAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TicketsFragment : Fragment() {

    private var cityFrom = EMPTY_STRING
    private var cityTo = EMPTY_STRING
    private var selectedDate = EMPTY_STRING
    private var selectedPassenger = 1

    private lateinit var binding: FragmentTicketsBinding
    private lateinit var ticketAdapter: TicketAdapter
    private val ticketsViewModel: TicketsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTicketsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ticketAdapter = TicketAdapter()
        checkArguments()
        setupDatePassengers()
        setupCities()
        ticketsViewModel.getTickets()
        initObservers()
        initListeners()
        initRecycler()
    }

    private fun checkArguments() {
        cityFrom = arguments?.getString(CITY_FROM) ?: EMPTY_STRING
        cityTo = arguments?.getString(CITY_TO) ?: EMPTY_STRING
        selectedDate = arguments?.getString(DATE) ?: EMPTY_STRING
        selectedPassenger = arguments?.getInt(PASSENGERS) ?: 1
    }

    private fun setupDatePassengers() {
        val datePassengersText = requireActivity().resources.getQuantityString(
            R.plurals.date_passengers_trip_title,
            selectedPassenger,
            selectedDate,
            selectedPassenger
        )
        binding.datePassengersTrip.text = datePassengersText
    }

    private fun setupCities() {
        binding.citiesTrip.text = requireActivity().getString(
            R.string.cities_trip_title,
            cityFrom,
            cityTo
        )
    }

    private fun initObservers() {
        ticketsViewModel.loadingState.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                showLoading()
            } else {
                hideLoading()
            }
        }
        ticketsViewModel.resultTickets.observe(viewLifecycleOwner) {
            setAdapter(it)
        }
        lifecycleScope.launch{
            ticketsViewModel.uiMessageChannel.collect {
                Toast.makeText(requireActivity(),getString(it), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setAdapter(list: Tickets){
        ticketAdapter.submitList(list.tickets.toMutableList())
    }

    private fun initListeners(){
        binding.btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun initRecycler(){
        binding.ticketsRv.adapter = ticketAdapter
    }
    companion object {
        @JvmStatic
        fun newInstance() = TicketsFragment()
    }
}