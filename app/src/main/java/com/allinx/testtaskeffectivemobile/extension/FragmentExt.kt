package com.allinx.testtaskeffectivemobile.extension

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.allinx.testtaskeffectivemobile.Constants.EmptyString.EMPTY_STRING
import com.allinx.testtaskeffectivemobile.Constants.SearchBottomSheetFragment.CITY_FROM
import com.allinx.testtaskeffectivemobile.Constants.SearchBottomSheetFragment.CITY_TO
import com.allinx.testtaskeffectivemobile.R
import com.allinx.testtaskeffectivemobile.presentation.main.MainActivity

fun Fragment.showLoading() {
    (activity as MainActivity).findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
}

fun Fragment.hideLoading() {
    (activity as MainActivity).findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
}

fun Fragment.checkArguments(): Pair<String,String> {
    val cityFrom = arguments?.getString(CITY_FROM) ?: EMPTY_STRING
    val cityTo = arguments?.getString(CITY_TO) ?: EMPTY_STRING
    return Pair(cityFrom, cityTo)
}

fun Fragment.setArguments(cityFrom: String, cityTo: String): Bundle {
    return bundleOf(CITY_FROM to cityFrom, CITY_TO to cityTo)
}