package com.dart69.trainingapp.trainings.presentation

import com.dart69.core.mapper.Mapper
import com.dart69.trainingapp.trainings.domain.models.Schedule
import com.dart69.trainingapp.trainings.presentation.models.SchedulesItem
import javax.inject.Inject

class SchedulesItemMapper @Inject constructor() :
    Mapper<List<@JvmSuppressWildcards Schedule>, List<@JvmSuppressWildcards SchedulesItem>> {
    override fun map(from: List<Schedule>): List<SchedulesItem> =
        from.groupBy { it.date }.flatMap { (date, schedules) ->
            listOf(SchedulesItem.Date(formatDate(date, PATTERN))) + schedules.map(SchedulesItem::Common)
        }

    private companion object {
        const val PATTERN = "EEEE, dd MMMM"
    }
}