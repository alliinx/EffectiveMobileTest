package com.allinx.data.storage.network.dto.tickets

import com.allinx.data.storage.network.dto.price.PriceDto
import com.allinx.data.storage.network.extension.GetPrice.getRmdPrice
import com.allinx.domain.models.ticket.TicketItem
import com.allinx.data.storage.network.extension.GetPrice.getThousandsPrice
import com.allinx.data.storage.network.extension.GetTime.getTime
import com.allinx.data.storage.network.extension.GetTime.getTripTime

data class TicketItemDto(
    val id: Int,
    val arrival: ArrivalDto,
    val badge: String?,
    val company: String,
    val departure: DepartureDto,
    val hand_luggage: HandLuggageDto,
    val has_transfer: Boolean,
    val has_visa_transfer: Boolean,
    val is_exchangable: Boolean,
    val is_returnable: Boolean,
    val luggage: LuggageDto,
    val price: PriceDto,
    val provider_name: String
) {
    fun toDomain(): TicketItem {
        return TicketItem(
            id = id,
            badge = badge,
            priceThousands = getThousandsPrice(price.value),
            priceRmd = getRmdPrice(price.value),
            departureAirport = departure.airport,
            arrivalAirport = arrival.airport,
            departureTime = getTime(departure.date),
            arrivalTime = getTime(arrival.date),
            timeTrip = getTripTime(departure.date, arrival.date),
            hasTransfer = has_transfer
        )
    }
}