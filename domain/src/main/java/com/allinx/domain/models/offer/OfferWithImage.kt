package com.allinx.domain.models.offer

data class OfferWithImage (
    val id: Int,
    val title: String,
    val town: String,
    val price: Int,
    val image: String
)