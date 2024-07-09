package com.allinx.testtaskeffectivemobile.presentation.tickets.offers.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.allinx.domain.models.offer.OfferWithImage

class OfferAdapter : ListAdapter<OfferWithImage, OfferViewHolder>(OfferDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        OfferViewHolder.create(parent)

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: MutableList<OfferWithImage>?) {
        super.submitList(
            if (list == null) null
            else ArrayList(list)
        )
    }
}