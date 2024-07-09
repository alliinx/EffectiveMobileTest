package com.allinx.data.storage.database.mapper

import com.allinx.data.storage.database.entity.ImageOfferEntity
import com.allinx.domain.models.offer.ImageOffer

object ImageOfferMapper {
    fun toEntity(imageOffer: ImageOffer): ImageOfferEntity {
        return ImageOfferEntity(
            id = imageOffer.id,
            image = imageOffer.image
        )
    }

    fun fromEntity(imageOfferEntity: ImageOfferEntity): ImageOffer {
        return ImageOffer(
            id = imageOfferEntity.id,
            image = imageOfferEntity.image
        )
    }
}