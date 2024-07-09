package com.allinx.testtaskeffectivemobile.presentation.tickets.alltickets.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.allinx.domain.models.ticket.TicketItem
import com.allinx.testtaskeffectivemobile.Constants.EmptyString.EMPTY_STRING
import com.allinx.testtaskeffectivemobile.R
import com.allinx.testtaskeffectivemobile.databinding.ItemTicketsBinding

class TicketViewHolder (
    private val binding: ItemTicketsBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(ticketItem: TicketItem) {
        val context = this.itemView.context
        with(binding) {
            departureTime.text = ticketItem.departureTime
            arrivalTime.text = ticketItem.arrivalTime
            departureAirport.text = ticketItem.departureAirport
            arrivalAirport.text = ticketItem.arrivalAirport
            tripTime.text = context.getString(R.string.trip_time, ticketItem.timeTrip)
            priceTicket.text = context.getString(
                R.string.price_offers_tickets,
                ticketItem.priceThousands,
                ticketItem.priceRmd
            )
            if(!ticketItem.hasTransfer){
                slash.text = context.getString(R.string.slash)
                hasTransfer.text = context.getString(R.string.no_transfer_title)
            }
            else{
                slash.text = EMPTY_STRING
                hasTransfer.text = EMPTY_STRING
            }
            if(ticketItem.badge!=null){
                badge.text = ticketItem.badge
            }
            else {
                cardViewBadge.visibility = View.GONE
            }
        }
    }

    companion object {
        fun create(
            parent: ViewGroup
        ) = TicketViewHolder(
            ItemTicketsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}