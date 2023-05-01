package com.dart69.trainingapp.trainings.presentation

import com.dart69.core.mapper.Mapper
import com.dart69.trainingapp.trainings.domain.models.Schedule
import com.dart69.trainingapp.trainings.presentation.models.SchedulesItem
import java.util.TreeMap
import javax.inject.Inject

class SchedulesItemMapper @Inject constructor() :
    Mapper<List<@JvmSuppressWildcards Schedule>, List<@JvmSuppressWildcards SchedulesItem>> {
    override fun map(from: List<Schedule>): List<SchedulesItem> =
        from.groupByTo(TreeMap(), Schedule::date).flatMap { (date, schedules) ->
            listOf(SchedulesItem.Date(date.toDateString(PATTERN))) + schedules.map(SchedulesItem::Common)
        }

    private companion object {
        const val PATTERN = "EEEE, dd MMMM"
    }
}