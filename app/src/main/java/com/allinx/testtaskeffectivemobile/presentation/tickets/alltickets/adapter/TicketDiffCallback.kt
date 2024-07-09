package com.allinx.testtaskeffectivemobile.presentation.tickets.alltickets.adapter

import androidx.recyclerview.widget.DiffUtil
import com.allinx.domain.models.ticket.TicketItem

class TicketDiffCallback : DiffUtil.ItemCallback<TicketItem>() {
    override fun areItemsTheSame(
        oldItem: TicketItem,
        newItem: TicketItem
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: TicketItem,
        newItem: TicketItem
    ): Boolean = oldItem == newItem
}