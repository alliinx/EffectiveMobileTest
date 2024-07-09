package com.allinx.domain.usecases

import com.allinx.domain.repository.ITicketsRepository

class GetSavedCityFromUseCase (private val repository: ITicketsRepository) {
    fun execute(): String? {
        return repository.getSavedCityFrom()
    }
}