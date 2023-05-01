package com.dart69.trainingapp.trainings.data.datasource

import com.dart69.core.mapper.Mapper
import com.dart69.trainingapp.trainings.data.models.SchedulesResponse
import com.dart69.trainingapp.trainings.domain.models.Schedule
import javax.inject.Inject

class SchedulesRemoteDataSource @Inject constructor(
    private val api: SchedulesApi,
    private val mapper: Mapper<SchedulesResponse, List<@JvmSuppressWildcards Schedule>>,
) : BaseRemoteDataSource(), SchedulesDataSource.Remote {

    override suspend fun loadSchedules(): List<Schedule> {
        val schedules = wrapResponse(api::loadSchedules)
        return mapper.map(schedules)
    }
}