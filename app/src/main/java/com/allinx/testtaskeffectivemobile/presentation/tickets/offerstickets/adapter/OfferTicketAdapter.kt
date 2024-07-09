package com.allinx.testtaskeffectivemobile.presentation.tickets.offerstickets.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.allinx.testtaskeffectivemobile.presentation.tickets.offerstickets.model.OfferTicketWithColor

class OfferTicketAdapter : ListAdapter<OfferTicketWithColor,
        OfferTicketViewHolder>(OfferTicketDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        OfferTicketViewHolder.create(parent)

    override fun onBindViewHolder(holder: OfferTicketViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: MutableList<OfferTicketWithColor>?) {
        super.submitList(
            if (list == null) null
            else ArrayList(list)
        )
    }
}