package com.dart69.trainingapp.trainings.data

import android.graphics.Color
import com.dart69.core.mapper.Mapper
import com.dart69.trainingapp.trainings.data.models.SchedulesResponse
import com.dart69.trainingapp.trainings.domain.models.Schedule
import com.dart69.trainingapp.trainings.presentation.formatToTime
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class SchedulesMapper @Inject constructor() : Mapper<SchedulesResponse, List<@JvmSuppressWildcards Schedule>> {
    override fun map(from: SchedulesResponse): List<Schedule> =
        from.lessons.map { dto ->
            Schedule(
                id = dto.appointment_id.toInt(),
                startTime = dto.startTime,
                endTime = dto.endTime,
                name = dto.name,
                trainer = from.trainers.firstOrNull { it.id == dto.coach_id }?.name.orEmpty(),
                date = dto.date.formatToTime(),
                location = dto.place,
                color = Color.parseColor(dto.color),
            )
        }
}