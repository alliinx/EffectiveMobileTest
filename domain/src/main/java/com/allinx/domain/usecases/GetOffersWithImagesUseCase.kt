package com.allinx.domain.usecases

import com.allinx.domain.models.offer.OfferWithImage
import com.allinx.domain.repository.ITicketsRepository

class GetOffersWithImagesUseCase (private val repository: ITicketsRepository) {
    suspend fun execute(): List<OfferWithImage> {
        return repository.getOffersWithImages()
    }
}