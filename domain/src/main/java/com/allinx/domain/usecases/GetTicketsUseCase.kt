package com.allinx.domain.usecases

import com.allinx.domain.models.ticket.Tickets
import com.allinx.domain.repository.ITicketsRepository

class GetTicketsUseCase (private val repository: ITicketsRepository) {
    suspend fun execute(): Tickets {
        return repository.getTickets()
    }
}