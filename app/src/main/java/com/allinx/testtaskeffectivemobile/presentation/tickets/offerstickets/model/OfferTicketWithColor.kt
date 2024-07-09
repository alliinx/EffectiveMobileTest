package com.allinx.testtaskeffectivemobile.presentation.tickets.offerstickets.model

import com.allinx.domain.models.offerticket.OfferTicketItem
import com.allinx.testtaskeffectivemobile.R

data class OfferTicketWithColor (
    val id: Int,
    val title: String,
    val timeRange: String,
    val price: Int,
    val color: Int
)