package com.allinx.testtaskeffectivemobile.presentation.tickets.searchBottomSheet.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.allinx.testtaskeffectivemobile.databinding.ItemPopularTripBinding
import com.allinx.testtaskeffectivemobile.presentation.tickets.searchBottomSheet.model.PopularTrip

class PopularTripViewHolder (
    private val binding: ItemPopularTripBinding,
    private val action: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(popularTrip: PopularTrip) {
        with(binding) {
            titlePopularTrip.text = popularTrip.title
            imgPopularTrip.load(popularTrip.image)
        }
        itemView.setOnClickListener {
            action(popularTrip.title)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            action: (String) -> Unit
        ) = PopularTripViewHolder(
            ItemPopularTripBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            action
        )
    }
}