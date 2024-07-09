package com.allinx.testtaskeffectivemobile.presentation.tickets.offers.adapter

import androidx.recyclerview.widget.DiffUtil
import com.allinx.domain.models.offer.OfferWithImage

class OfferDiffCallBack: DiffUtil.ItemCallback<OfferWithImage>() {
    override fun areItemsTheSame(
        oldItem: OfferWithImage,
        newItem: OfferWithImage
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: OfferWithImage,
        newItem: OfferWithImage
    ): Boolean = oldItem == newItem
}