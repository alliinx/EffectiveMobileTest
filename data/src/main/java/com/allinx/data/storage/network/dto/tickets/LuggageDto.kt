package com.allinx.data.storage.network.dto.tickets

import com.allinx.data.storage.network.dto.price.PriceDto

data class LuggageDto(
    val has_luggage: Boolean,
    val price: PriceDto
)