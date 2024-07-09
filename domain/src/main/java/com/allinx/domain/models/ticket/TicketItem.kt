package com.allinx.domain.models.ticket

data class TicketItem (
    val id: Int,
    val badge: String?,
    val priceThousands: Int,
    val priceRmd: String,
    val departureAirport: String,
    val arrivalAirport: String,
    val departureTime: String,
    val arrivalTime: String,
    val timeTrip: String,
    val hasTransfer: Boolean
)


