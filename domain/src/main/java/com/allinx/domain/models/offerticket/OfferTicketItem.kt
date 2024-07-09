package com.allinx.domain.models.offerticket

data class OfferTicketItem (
    val id: Int,
    val title: String,
    val timeRange: List<String>,
    val price: Int
)
