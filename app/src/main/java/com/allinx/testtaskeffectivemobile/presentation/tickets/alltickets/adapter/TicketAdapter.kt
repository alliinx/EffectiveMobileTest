package com.allinx.testtaskeffectivemobile.presentation.tickets.alltickets.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.allinx.domain.models.ticket.TicketItem

class TicketAdapter : ListAdapter<TicketItem, TicketViewHolder>(TicketDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TicketViewHolder.create(parent)

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: MutableList<TicketItem>?) {
        super.submitList(
            if (list == null) null
            else ArrayList(list)
        )
    }
}