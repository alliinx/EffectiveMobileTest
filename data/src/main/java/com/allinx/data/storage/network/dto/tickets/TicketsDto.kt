package com.allinx.data.storage.network.dto.tickets

import com.allinx.domain.models.ticket.Tickets

data class TicketsDto(
    val tickets: List<TicketItemDto>
) {
    fun toDomain(): Tickets {
        val domainTickets = tickets.map{
            it.toDomain()
        }
        return Tickets(
            tickets = domainTickets
        )
    }
}