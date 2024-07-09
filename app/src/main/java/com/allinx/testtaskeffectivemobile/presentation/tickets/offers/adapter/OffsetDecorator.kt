package com.allinx.testtaskeffectivemobile.presentation.tickets.offers.adapter

import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class OffsetDecorator (
    private val offset: Int,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val space = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            offset.toFloat(),
            view.resources.displayMetrics
        ).toInt()
        parent.adapter?.let { adapter ->
            if(parent.getChildAdapterPosition(view) != adapter.itemCount-1){
                outRect.right = space;
            }
        }
    }
}