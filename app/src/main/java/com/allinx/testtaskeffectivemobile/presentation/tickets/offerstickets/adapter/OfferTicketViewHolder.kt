package com.allinx.testtaskeffectivemobile.presentation.tickets.offerstickets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.allinx.testtaskeffectivemobile.R
import com.allinx.testtaskeffectivemobile.databinding.ItemOfferTicketBinding
import com.allinx.testtaskeffectivemobile.extension.Converter.getStringPrice
import com.allinx.testtaskeffectivemobile.presentation.tickets.offerstickets.model.OfferTicketWithColor

class OfferTicketViewHolder (
    private val binding: ItemOfferTicketBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(offerTicketWithColor: OfferTicketWithColor) {
        val context = this.itemView.context
        with(binding) {
            titleOfferTicket.text = offerTicketWithColor.title
            timeOfferTicket.text = offerTicketWithColor.timeRange
            priceOfferTicket.text = context.getString(
                R.string.price_offers_tickets,
                offerTicketWithColor.price/1000,
                getStringPrice(offerTicketWithColor.price))
            circle.backgroundTintList = ContextCompat.getColorStateList(context, offerTicketWithColor.color)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup
        ) = OfferTicketViewHolder(
            ItemOfferTicketBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}