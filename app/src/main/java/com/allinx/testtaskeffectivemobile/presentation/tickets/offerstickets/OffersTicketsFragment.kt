package com.allinx.testtaskeffectivemobile.presentation.tickets.offerstickets

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.allinx.domain.models.offerticket.OffersTickets
import com.allinx.testtaskeffectivemobile.Constants.EmptyString.EMPTY_STRING
import com.allinx.testtaskeffectivemobile.Constants.OffersTickets.DATE
import com.allinx.testtaskeffectivemobile.Constants.OffersTickets.FIND_NUMBERS_PATTERN
import com.allinx.testtaskeffectivemobile.Constants.OffersTickets.PASSENGERS
import com.allinx.testtaskeffectivemobile.Constants.OffersTicketsFragment.DATE_FORMAT_PATTERN
import com.allinx.testtaskeffectivemobile.Constants.OffersTicketsFragment.DATE_FORMAT_PATTERN_FULL_MONTH_DAY
import com.allinx.testtaskeffectivemobile.Constants.SearchBottomSheetFragment.CITY_FROM
import com.allinx.testtaskeffectivemobile.Constants.SearchBottomSheetFragment.CITY_TO
import com.allinx.testtaskeffectivemobile.Constants.SearchBottomSheetFragment.SHOW_BOTTOM_SHEET_FRAGMENT
import com.allinx.testtaskeffectivemobile.R
import com.allinx.testtaskeffectivemobile.databinding.FragmentOffersTicketsBinding
import com.allinx.testtaskeffectivemobile.extension.InputFilter.cyrillicFilter
import com.allinx.testtaskeffectivemobile.extension.checkArguments
import com.allinx.testtaskeffectivemobile.extension.hideLoading
import com.allinx.testtaskeffectivemobile.extension.setArguments
import com.allinx.testtaskeffectivemobile.extension.showLoading
import com.allinx.testtaskeffectivemobile.presentation.tickets.alltickets.TicketsFragment
import com.allinx.testtaskeffectivemobile.presentation.tickets.offerstickets.adapter.ListDividerItemDecoration
import com.allinx.testtaskeffectivemobile.presentation.tickets.offerstickets.adapter.OfferTicketAdapter
import com.allinx.testtaskeffectivemobile.presentation.tickets.offerstickets.mapper.OfferTicketMapper.toOfferTicketWithColor
import com.allinx.testtaskeffectivemobile.presentation.tickets.searchBottomSheet.SearchBottomSheetFragment
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar
import java.util.Locale
import java.util.regex.Matcher
import java.util.regex.Pattern


@AndroidEntryPoint
class OffersTicketsFragment : Fragment() {

    private var cityFrom = EMPTY_STRING
    private var cityTo = EMPTY_STRING
    private var selectedDate = EMPTY_STRING

    private lateinit var binding: FragmentOffersTicketsBinding
    private lateinit var offerTicketAdapter: OfferTicketAdapter
    private val offerTicketViewModel: OffersTicketsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOffersTicketsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        offerTicketAdapter = OfferTicketAdapter()
        offerTicketViewModel.getOffersTickets()
        setEditText()
        setupFilters()
        setChipDate()
        initObservers()
        initListeners()
        initRecycler()
    }

    private fun setupFilters() {
        binding.searchViewsOffersTickets.editTextToOffersTicketsScreen.filters = arrayOf(cyrillicFilter)
    }

    private fun setEditText() {
        val pair = checkArguments()
        cityFrom = pair.first
        cityTo = pair.second

        binding.searchViewsOffersTickets.textFromOffersTicketsScreen.text = cityFrom
        binding.searchViewsOffersTickets.editTextToOffersTicketsScreen.setText(cityTo)
    }

    private fun setChipDate(){
        val str = getDateStr(Date(), DATE_FORMAT_PATTERN)
        selectedDate = getDateStr(Date(), DATE_FORMAT_PATTERN_FULL_MONTH_DAY)
        binding.chipSearch.chipDateTrip.text = spanText(str)
    }

    private fun getDateStr(dateUnformatted: Date, datePattern: String): String {
        val dateFormat = SimpleDateFormat(datePattern, Locale.getDefault())
        val dateFormatted = dateFormat.format(dateUnformatted)
        val date = dateFormatted.split('-')
        if(datePattern== DATE_FORMAT_PATTERN){
            val month = date[1].replace(".","")
            return date[0] + " " + month + ", " + date[2]
        }else{
            return date[0] + " " + date[1]
        }
    }

    private fun spanText(str: String): SpannableStringBuilder {
        val start = str.indexOf(',')
        val text = SpannableStringBuilder(str)
        val style = ForegroundColorSpan(requireContext().getColor(R.color.dark_grey))
        text.setSpan(style, start, str.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        return text
    }

    private fun initObservers() {
        offerTicketViewModel.loadingState.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                showLoading()
            } else {
                hideLoading()
            }
        }
        offerTicketViewModel.resultOffersTickets.observe(viewLifecycleOwner) {
            setAdapter(it)
        }
        lifecycleScope.launch{
            offerTicketViewModel.uiMessageChannel.collect {
                Toast.makeText(requireActivity(),getString(it), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setAdapter(list: OffersTickets){
        val listRv = list.offersTickets.map {
            toOfferTicketWithColor(it)
        }
        offerTicketAdapter.submitList(listRv.toMutableList())
    }

    private fun initListeners() {
        initSearchListeners()
        initChipListeners()
        initBtnListeners()
    }

    private fun initSearchListeners(){
        with(binding){
            btnBack.setOnClickListener {
                parentFragmentManager.popBackStack()
                val fragment = SearchBottomSheetFragment()
                fragment.arguments = setArguments(cityFrom,getCityTo())
                fragment.show(parentFragmentManager, SHOW_BOTTOM_SHEET_FRAGMENT)
            }
            with(searchViewsOffersTickets){
                btnClose.setOnClickListener {
                    searchViewsOffersTickets.editTextToOffersTicketsScreen.setText(EMPTY_STRING)
                }
                btnReverse.setOnClickListener {
                    cityFrom = cityTo.apply {
                        cityTo = cityFrom
                    }
                    textFromOffersTicketsScreen.text = cityFrom
                    editTextToOffersTicketsScreen.setText(cityTo)
                }
                editTextToOffersTicketsScreen.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable) {
                        cityTo = editTextToOffersTicketsScreen.text.toString()
                    }
                    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int
                    ) {}
                    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int
                    ) {}
                })
            }
        }
    }

    private fun initChipListeners() {
        with(binding){
            with(chipSearch){
                chipDateTrip.setOnClickListener {
                    showDatePickerDialog(chipDateTrip)
                }
                chipBackDateTrip.setOnClickListener {
                    showDatePickerDialog(chipBackDateTrip)
                    chipBackDateTrip.chipIcon = null
                }
            }
        }
    }

    private fun initBtnListeners(){
        with(binding){
            switcher.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    Toast.makeText(requireActivity(),getString(R.string.reminder_switcher_toast_true),
                        Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(requireActivity(),getString(R.string.reminder_switcher_toast_false),
                        Toast.LENGTH_SHORT).show()
                }
            }
            btnShowAllTickets.setOnClickListener {
                val arguments = bundleOf(CITY_FROM to cityFrom, CITY_TO to cityTo,
                    DATE to selectedDate, PASSENGERS to getPassengers())
                parentFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, TicketsFragment::class.java, arguments)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    private fun getPassengers(): Int {
        var num = EMPTY_STRING
        val pat: Pattern = Pattern.compile(FIND_NUMBERS_PATTERN)
        val matcher: Matcher = pat.matcher(binding.chipSearch.chipPassengers.text.toString())
        while (matcher.find()) {
            num = matcher.group()
        }
        return num.toInt()
    }

    private fun showDatePickerDialog(chip: Chip) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
                val strPickDate = getDateStr(GregorianCalendar(selectedYear, selectedMonth, selectedDay).time,
                    DATE_FORMAT_PATTERN)
                chip.text = spanText(strPickDate)
                if(chip == binding.chipSearch.chipDateTrip){
                    val pickMonthDay = getDateStr(GregorianCalendar(selectedYear, selectedMonth, selectedDay).time,
                        DATE_FORMAT_PATTERN_FULL_MONTH_DAY)
                    selectedDate = pickMonthDay
                }
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun getCityTo(): String {
        return binding.searchViewsOffersTickets.editTextToOffersTicketsScreen.text.toString()
    }

    private fun initRecycler() {
        binding.rvOffersTickets.apply {
            adapter = offerTicketAdapter
            addItemDecoration(
                ListDividerItemDecoration(
                    color = requireContext().getColor(R.color.payne_s_grey),
                    heightPx = 2
                )
            )
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = OffersTicketsFragment()
    }
}