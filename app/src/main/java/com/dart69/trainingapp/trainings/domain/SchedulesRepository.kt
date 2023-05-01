package com.dart69.trainingapp.trainings.domain

import com.dart69.core.results.ResultsFlow
import com.dart69.trainingapp.trainings.domain.models.Schedule

interface SchedulesRepository {
    fun loadSchedules(options: Options = Options.LOAD_FROM_CACHE): ResultsFlow<List<Schedule>>

    enum class Options {
        FORCE_REFRESH, LOAD_FROM_CACHE
    }
}