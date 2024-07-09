package com.allinx.data.storage.network.dto.offertickets

import com.allinx.domain.models.offerticket.OffersTickets

data class OffersTicketsDto (
    val tickets_offers: List<OfferTicketItemDto>
) {
    fun toDomain(): OffersTickets {
        val domainOffersTickets = tickets_offers.map{
            it.toDomain()
        }
        return OffersTickets(
            offersTickets = domainOffersTickets
        )
    }
}