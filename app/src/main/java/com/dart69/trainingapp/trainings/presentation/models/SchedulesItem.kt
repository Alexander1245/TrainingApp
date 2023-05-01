package com.dart69.trainingapp.trainings.presentation.models

import com.dart69.trainingapp.trainings.domain.models.Schedule

sealed class SchedulesItem {
    abstract fun areItemsTheSame(newItem: SchedulesItem): Boolean

    data class Date(val date: String) : SchedulesItem() {
        override fun areItemsTheSame(newItem: SchedulesItem): Boolean =
            newItem is Date && newItem.date == date
    }

    data class Common(val schedule: Schedule): SchedulesItem() {
        override fun areItemsTheSame(newItem: SchedulesItem): Boolean =
            newItem is Common && newItem.schedule.id == schedule.id
    }
}
