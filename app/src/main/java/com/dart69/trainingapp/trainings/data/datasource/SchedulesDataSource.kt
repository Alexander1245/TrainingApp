package com.dart69.trainingapp.trainings.data.datasource

import com.dart69.trainingapp.trainings.domain.models.Schedule

interface SchedulesDataSource {
    suspend fun loadSchedules(): List<Schedule>

    interface Remote : SchedulesDataSource

    interface Cached : SchedulesDataSource {
        fun cache(schedules: List<Schedule>)
    }
}