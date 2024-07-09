package com.allinx.data.storage.network.dto.offer

import com.allinx.domain.models.offer.Offers

data class OffersDto (
    val offers: List<OfferItemDto>
) {
    fun toDomain(): Offers {
        val domainOffers = offers.map{
            it.toDomain()
        }
        return Offers(
            offers = domainOffers
        )
    }
}
