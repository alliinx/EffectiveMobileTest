package com.allinx.testtaskeffectivemobile.presentation.tickets.offers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.allinx.testtaskeffectivemobile.databinding.ItemOfferBinding
import coil.load
import com.allinx.domain.models.offer.OfferWithImage
import com.allinx.testtaskeffectivemobile.R
import com.allinx.testtaskeffectivemobile.extension.Converter.getStringPrice

class OfferViewHolder (
    private val binding: ItemOfferBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(offer: OfferWithImage) {
        val context = this.itemView.context
        with(binding) {
            titleOffer.text = offer.title
            cityOffer.text = offer.town
            priceOffer.text = context.getString(R.string.price_offers,
                offer.price/1000,
                getStringPrice(offer.price))
            imgChildSavedRv.load(offer.image)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup
        ) = OfferViewHolder(
            ItemOfferBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}