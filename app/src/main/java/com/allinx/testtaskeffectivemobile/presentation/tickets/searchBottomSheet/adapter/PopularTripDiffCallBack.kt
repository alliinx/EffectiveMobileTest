package com.allinx.testtaskeffectivemobile.presentation.tickets.searchBottomSheet.adapter

import androidx.recyclerview.widget.DiffUtil
import com.allinx.testtaskeffectivemobile.presentation.tickets.searchBottomSheet.model.PopularTrip

class PopularTripDiffCallBack: DiffUtil.ItemCallback<PopularTrip>() {
    override fun areItemsTheSame(
        oldItem: PopularTrip,
        newItem: PopularTrip
    ): Boolean = oldItem.title == newItem.title

    override fun areContentsTheSame(
        oldItem: PopularTrip,
        newItem: PopularTrip
    ): Boolean = oldItem == newItem
}