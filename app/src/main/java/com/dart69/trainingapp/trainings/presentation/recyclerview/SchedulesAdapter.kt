package com.dart69.trainingapp.trainings.presentation.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.dart69.trainingapp.databinding.ItemDateBinding
import com.dart69.trainingapp.databinding.ItemScheduleBinding
import com.dart69.trainingapp.trainings.presentation.models.SchedulesItem

typealias ItemViewHolder = BaseViewHolder<SchedulesItem, out ViewBinding>

class SchedulesAdapter : ListAdapter<SchedulesItem, ItemViewHolder>(SchedulesDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        when(viewType) {
            ViewType.SCHEDULE.ordinal -> SchedulesViewHolder(
                ItemScheduleBinding.inflate(parent.layoutInflater, parent, false)
            )
            ViewType.DATE.ordinal -> DateViewHolder(
                ItemDateBinding.inflate(parent.layoutInflater, parent, false)
            )
            else -> throw IllegalArgumentException("Unknown view type $viewType")
        }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(currentList[position])

    override fun getItemViewType(position: Int): Int = when(currentList[position]) {
        is SchedulesItem.Date -> ViewType.DATE.ordinal
        is SchedulesItem.Common -> ViewType.SCHEDULE.ordinal
    }

    enum class ViewType {
        SCHEDULE, DATE
    }
}