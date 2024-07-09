package com.allinx.data.storage.network.dto.offertickets

import com.allinx.data.storage.network.dto.price.PriceDto
import com.allinx.domain.models.offerticket.OfferTicketItem

data class OfferTicketItemDto (
    val id: Int,
    val title: String,
    val time_range: List<String>,
    val price: PriceDto
){
    fun toDomain(): OfferTicketItem {
        return OfferTicketItem(
            id = id,
            title = title,
            timeRange = time_range,
            price = price.value
        )
    }
}