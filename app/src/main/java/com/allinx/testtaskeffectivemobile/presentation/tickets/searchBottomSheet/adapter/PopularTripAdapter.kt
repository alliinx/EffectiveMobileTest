package com.allinx.testtaskeffectivemobile.presentation.tickets.searchBottomSheet.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.allinx.testtaskeffectivemobile.presentation.tickets.searchBottomSheet.model.PopularTrip

class PopularTripAdapter (
    private val action: (String) -> Unit
) : ListAdapter<PopularTrip, PopularTripViewHolder>(PopularTripDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PopularTripViewHolder.create(parent, action)

    override fun onBindViewHolder(holder: PopularTripViewHolder, position: Int) =
        holder.bind(getItem(position))

    override fun submitList(list: MutableList<PopularTrip>?) {
        super.submitList(
            if (list == null) null
            else ArrayList(list)
        )
    }
}