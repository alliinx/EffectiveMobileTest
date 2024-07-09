package com.allinx.domain.usecases

import com.allinx.domain.repository.ITicketsRepository

class SaveCityFromUseCase (private val repository: ITicketsRepository) {
    fun execute(city: String) {
        return repository.saveCityFrom(city)
    }
}