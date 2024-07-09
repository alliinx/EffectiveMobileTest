package com.allinx.domain.repository

import com.allinx.domain.models.offer.ImageOffer
import com.allinx.domain.models.offer.OfferWithImage
import com.allinx.domain.models.offer.Offers
import com.allinx.domain.models.offerticket.OffersTickets
import com.allinx.domain.models.ticket.Tickets

interface ITicketsRepository {
    suspend fun getOffers(): Offers
    suspend fun getOffersTickets(): OffersTickets
    suspend fun getTickets(): Tickets
    fun getSavedCityFrom(): String?
    fun saveCityFrom(city: String)
    suspend fun getImageOfferById(id: Int): ImageOffer
    suspend fun getImagesOffers(): List<ImageOffer>
    suspend fun getOffersWithImages(): List<OfferWithImage>
}