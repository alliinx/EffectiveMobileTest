package com.allinx.testtaskeffectivemobile.presentation.tickets.offers

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.allinx.domain.models.offer.OfferWithImage
import com.allinx.testtaskeffectivemobile.Constants.EmptyString.EMPTY_STRING
import com.allinx.testtaskeffectivemobile.Constants.OfferFragment.MARGIN_END
import com.allinx.testtaskeffectivemobile.Constants.SearchBottomSheetFragment.CITY_FROM
import com.allinx.testtaskeffectivemobile.Constants.SearchBottomSheetFragment.CITY_TO
import com.allinx.testtaskeffectivemobile.Constants.SearchBottomSheetFragment.SHOW_BOTTOM_SHEET_FRAGMENT
import com.allinx.testtaskeffectivemobile.databinding.FragmentOffersBinding
import com.allinx.testtaskeffectivemobile.extension.InputFilter.cyrillicFilter
import com.allinx.testtaskeffectivemobile.extension.hideLoading
import com.allinx.testtaskeffectivemobile.extension.showLoading
import com.allinx.testtaskeffectivemobile.presentation.tickets.offers.adapter.OfferAdapter
import com.allinx.testtaskeffectivemobile.presentation.tickets.offers.adapter.OffsetDecorator
import com.allinx.testtaskeffectivemobile.presentation.tickets.searchBottomSheet.SearchBottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.GregorianCalendar
import java.util.Locale
import java.util.regex.Matcher
import java.util.regex.Pattern


@AndroidEntryPoint
class OffersFragment : Fragment() {

    private lateinit var binding: FragmentOffersBinding
    private lateinit var offerAdapter: OfferAdapter
    private val offerViewModel: OffersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOffersBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        offerAdapter = OfferAdapter()
        offerViewModel.getOffers()
        offerViewModel.getSavedCity()
        setupFilters()
        initObservers()
        initListeners()
        initRecycler()
    }

    private fun setupFilters() {
        binding.searchOffersScreen.editTextToOffersScreen.filters =arrayOf(cyrillicFilter)
        binding.searchOffersScreen.editTextFromOffersScreen.filters =arrayOf(cyrillicFilter)
    }

    private fun initObservers() {
        observeOffers()
        observeSavedCity()
        observeUiMessageChannel()
        observeLoadingState()
    }

    private fun observeOffers() {
        offerViewModel.resultOffers.observe(viewLifecycleOwner) {
            setAdapter(it)
        }
    }

    private fun observeSavedCity() {
        offerViewModel.resultSavedCity.observe(viewLifecycleOwner) {
            setEditText(it)
        }
    }

    private fun observeUiMessageChannel() {
        lifecycleScope.launch{
            offerViewModel.uiMessageChannel.collect {
                Toast.makeText(requireActivity(),getString(it), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeLoadingState() {
        offerViewModel.loadingState.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                showLoading()
            } else {
                hideLoading()
            }
        }
    }

    private fun initListeners() {
        with(binding) {
            searchOffersScreen.editTextFromOffersScreen.setOnKeyListener(object : View.OnKeyListener {
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                    if (event.action == KeyEvent.ACTION_DOWN &&
                        keyCode == KeyEvent.KEYCODE_ENTER
                    ) {
                        offerViewModel.saveCity(searchOffersScreen.editTextFromOffersScreen.text.toString())
                        return true
                    }
                    return false
                }
            })
            searchOffersScreen.editTextToOffersScreen.setOnFocusChangeListener { _, hasFocus ->
                if(hasFocus){
                    offerViewModel.saveCity(searchOffersScreen.editTextFromOffersScreen.text.toString())
                    openSearchBottomSheetFragment(searchOffersScreen.editTextFromOffersScreen.text.toString())
                }
            }
        }
    }

    private fun initRecycler() {
        binding.offersRv.apply{
            adapter = offerAdapter
            addItemDecoration(
                OffsetDecorator(
                    offset = MARGIN_END
                )
            )
        }
    }

    private fun setAdapter(list: List<OfferWithImage>) {
        offerAdapter.submitList(list.toMutableList())
    }

    private fun setEditText(city: String?) {
        binding.searchOffersScreen.editTextFromOffersScreen.setText(city)
    }

    private fun openSearchBottomSheetFragment(city: String){
        val arguments = bundleOf(CITY_FROM to city, CITY_TO to EMPTY_STRING)
        val fragment = SearchBottomSheetFragment()
        fragment.arguments = arguments
        fragment.show(parentFragmentManager,SHOW_BOTTOM_SHEET_FRAGMENT)
    }

    companion object {
        @JvmStatic
        fun newInstance() = OffersFragment()
    }
}