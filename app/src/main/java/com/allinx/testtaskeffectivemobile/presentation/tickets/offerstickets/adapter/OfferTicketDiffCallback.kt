package com.allinx.testtaskeffectivemobile.presentation.tickets.offerstickets.adapter

import androidx.recyclerview.widget.DiffUtil
import com.allinx.testtaskeffectivemobile.presentation.tickets.offerstickets.model.OfferTicketWithColor

class OfferTicketDiffCallback : DiffUtil.ItemCallback<OfferTicketWithColor>() {
    override fun areItemsTheSame(
        oldItem: OfferTicketWithColor,
        newItem: OfferTicketWithColor
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: OfferTicketWithColor,
        newItem: OfferTicketWithColor
    ): Boolean = oldItem == newItem
}