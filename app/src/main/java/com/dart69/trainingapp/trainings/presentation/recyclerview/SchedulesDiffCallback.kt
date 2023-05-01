package com.dart69.trainingapp.trainings.presentation.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.dart69.trainingapp.trainings.presentation.models.SchedulesItem

class SchedulesDiffCallback : DiffUtil.ItemCallback<SchedulesItem>() {
    override fun areItemsTheSame(oldItem: SchedulesItem, newItem: SchedulesItem): Boolean =
        oldItem.areItemsTheSame(newItem)

    override fun areContentsTheSame(oldItem: SchedulesItem, newItem: SchedulesItem): Boolean =
        oldItem == newItem
}