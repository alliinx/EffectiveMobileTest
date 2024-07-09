package com.allinx.data.storage.network.dto.offer

import com.allinx.data.storage.network.dto.price.PriceDto
import com.allinx.domain.models.offer.OfferItem

data class OfferItemDto (
    val id: Int,
    val price: PriceDto,
    val title: String,
    val town: String
) {
    fun toDomain(): OfferItem {
        return OfferItem(
            id = id,
            title = title,
            town = town,
            price = price.value
        )
    }
}