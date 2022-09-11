package com.mvvm.audioplayer.utils.helper

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridEqualSpacingItemDecorator(private val spanCount: Int, val marginSpan: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position >= 0) {
            val column = position % spanCount

            outRect.left = marginSpan - column * marginSpan / spanCount
            outRect.right = (column + 1) * marginSpan / spanCount

            if (position < spanCount) {
                outRect.top = marginSpan
            }
            outRect.bottom = marginSpan
        } else {
            outRect.left = 0
            outRect.right = 0
            outRect.top = 0
            outRect.bottom = 0
        }
    }
}
