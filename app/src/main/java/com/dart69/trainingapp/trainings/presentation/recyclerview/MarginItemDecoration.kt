package com.dart69.trainingapp.trainings.presentation.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class MarginItemDecoration(
    private val margin: Int = DEFAULT_MARGIN,
) : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildLayoutPosition(view)
        if(position == 0) {
            outRect.top = margin
        }
        outRect.left = margin
        outRect.right = margin
        outRect.bottom = margin
    }

    private companion object {
        const val DEFAULT_MARGIN = 10
    }
}