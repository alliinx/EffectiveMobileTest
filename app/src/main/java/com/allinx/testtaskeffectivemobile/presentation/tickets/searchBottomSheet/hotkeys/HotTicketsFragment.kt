package com.allinx.testtaskeffectivemobile.presentation.tickets.searchBottomSheet.hotkeys

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allinx.testtaskeffectivemobile.Constants.EmptyString.EMPTY_STRING
import com.allinx.testtaskeffectivemobile.Constants.SearchBottomSheetFragment.SHOW_BOTTOM_SHEET_FRAGMENT
import com.allinx.testtaskeffectivemobile.databinding.FragmentHotTicketsBinding
import com.allinx.testtaskeffectivemobile.extension.checkArguments
import com.allinx.testtaskeffectivemobile.extension.setArguments
import com.allinx.testtaskeffectivemobile.presentation.tickets.searchBottomSheet.SearchBottomSheetFragment


class HotTicketsFragment() : Fragment() {

    private var cityFrom = EMPTY_STRING
    private var cityTo = EMPTY_STRING

    private lateinit var binding: FragmentHotTicketsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHotTicketsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pair = checkArguments()
        cityFrom = pair.first
        cityTo = pair.second
        initListeners()
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
            val fragment = SearchBottomSheetFragment()
            fragment.arguments = setArguments(cityFrom,cityTo)
            fragment.show(parentFragmentManager, SHOW_BOTTOM_SHEET_FRAGMENT)
        }
    }
}