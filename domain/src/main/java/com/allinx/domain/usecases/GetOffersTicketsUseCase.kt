package com.allinx.domain.usecases

import com.allinx.domain.models.offerticket.OffersTickets
import com.allinx.domain.repository.ITicketsRepository

class GetOffersTicketsUseCase (private val repository: ITicketsRepository) {
    suspend fun execute(): OffersTickets {
        return repository.getOffersTickets()
    }
}