package com.allinx.testtaskeffectivemobile.presentation.tickets.searchBottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import com.allinx.testtaskeffectivemobile.Constants.EmptyString.EMPTY_STRING
import com.allinx.testtaskeffectivemobile.R
import com.allinx.testtaskeffectivemobile.databinding.FragmentSearchBottomSheetBinding
import com.allinx.testtaskeffectivemobile.extension.InputFilter.cyrillicFilter
import com.allinx.testtaskeffectivemobile.extension.checkArguments
import com.allinx.testtaskeffectivemobile.extension.setArguments
import com.allinx.testtaskeffectivemobile.presentation.tickets.offerstickets.OffersTicketsFragment
import com.allinx.testtaskeffectivemobile.presentation.tickets.searchBottomSheet.adapter.PopularTripAdapter
import com.allinx.testtaskeffectivemobile.presentation.tickets.searchBottomSheet.hotkeys.HardTripFragment
import com.allinx.testtaskeffectivemobile.presentation.tickets.searchBottomSheet.hotkeys.HotTicketsFragment
import com.allinx.testtaskeffectivemobile.presentation.tickets.searchBottomSheet.hotkeys.WeekendTripFragment
import com.allinx.testtaskeffectivemobile.presentation.tickets.searchBottomSheet.model.PopularTrip
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class SearchBottomSheetFragment () : BottomSheetDialogFragment() {

    private var cityFrom = EMPTY_STRING
    private var cityTo = EMPTY_STRING

    private lateinit var binding: FragmentSearchBottomSheetBinding
    private lateinit var popularTripAdapter: PopularTripAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBottomSheetBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        popularTripAdapter = PopularTripAdapter {
            setEditText(it)
        }
        setupFilters()
        setEditText()
        setupSearchViews()
        setAdapter()
        initRecycler()
        initListeners()
    }

    private fun setupFilters() {
        val searchViewFrom = binding.searchViewsBottomSheet.editTextToBottomSheet
        val txtSearchTo = searchViewFrom.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        txtSearchTo.filters = arrayOf(cyrillicFilter)
    }

    private fun setEditText() {
        val pair = checkArguments()
        cityFrom = pair.first
        cityTo = pair.second

        binding.searchViewsBottomSheet.textFromBottomSheet.text = cityFrom

        val searchViewFrom = binding.searchViewsBottomSheet.editTextToBottomSheet
        val txtSearchTo = searchViewFrom.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        txtSearchTo.setText(cityTo)
    }

    private fun setAdapter(){
        popularTripAdapter.submitList(createListPopularTrip().toMutableList())
    }

    private fun createListPopularTrip(): List<PopularTrip> {
        return listOf(
            PopularTrip(getString(R.string.stambul_title), "file:///android_asset/stambul.png"),
            PopularTrip(getString(R.string.sochi_title), "file:///android_asset/sochi.png"),
            PopularTrip(getString(R.string.phuket_title), "file:///android_asset/phuket.png")
        )
    }

    private fun setEditText(city: String) {
        val searchViewFrom = binding.searchViewsBottomSheet.editTextToBottomSheet
        val txtSearchTo = searchViewFrom.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        txtSearchTo.setText(city)
    }

    private fun initRecycler() {
        binding.popularTripRv.adapter = popularTripAdapter
    }

    private fun setupSearchViews(){
        val searchViewTo = binding.searchViewsBottomSheet.editTextToBottomSheet
        val txtSearchTo = searchViewTo.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        txtSearchTo.setHintTextColor(resources.getColor(R.color.dark_grey,null))
        txtSearchTo.setTextColor(resources.getColor(R.color.white,null))
    }

    private fun getCityTo(): String {
        val searchViewTo = binding.searchViewsBottomSheet.editTextToBottomSheet
        val txtSearchTo = searchViewTo.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        return txtSearchTo.text.toString()
    }

    private fun initListeners() {
        with(binding){
            hotKeys.btnHardTrip.setOnClickListener {
                replaceFragment(HardTripFragment())
            }
            hotKeys.btnEverywhereTrip.setOnClickListener {
                setEditText(getString(R.string.everywhere_trip_title))
            }
            hotKeys.btnWeekendTrip.setOnClickListener {
                replaceFragment(WeekendTripFragment())
            }
            hotKeys.btnHotTickets.setOnClickListener {
                replaceFragment(HotTicketsFragment())
            }

            searchViewsBottomSheet.editTextToBottomSheet.setOnQueryTextListener(object : OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    replaceFragment(OffersTicketsFragment())
                    return true
                }
                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }
            })
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment::class.java, setArguments(cityFrom, getCityTo()))
            .addToBackStack(null)
            .commit()
        dismiss()
    }

//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
//        dialog.setOnShowListener {
//            val bottomSheetDialog = it as BottomSheetDialog
//            val parentLayout =
//                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
//            parentLayout?.let { layout ->
//                val behaviour = BottomSheetBehavior.from(layout)
//                setupFullHeight(layout)
//                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
//            }
//        }
//        //dialog.window?.setWindowAnimations(R.style.DialogAnimation)
//        return dialog
//    }
//
//    private fun setupFullHeight(bottomSheet: View) {
//        val layoutParams = bottomSheet.layoutParams
//        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
//        bottomSheet.layoutParams = layoutParams
//    }
}