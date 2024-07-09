package com.allinx.data.storage.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.allinx.domain.models.offer.ImageOffer

@Entity(tableName = "images_offers")
data class ImageOfferEntity(
    @PrimaryKey val id: Int,
    val image: String
){
    fun toDomain(): ImageOffer {
        return ImageOffer(
            id = id,
            image = image
        )
    }
}
