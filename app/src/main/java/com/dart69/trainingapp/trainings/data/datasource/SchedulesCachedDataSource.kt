package com.dart69.trainingapp.trainings.data.datasource

import com.dart69.data.cache.Cache
import com.dart69.data.cache.CacheImpl
import com.dart69.trainingapp.trainings.domain.models.Schedule

class SchedulesCachedDataSource(
    private val cache: Cache.Mutable<Unit, List<Schedule>> = CacheImpl()
) : SchedulesDataSource.Cached {
    override suspend fun loadSchedules(): List<Schedule> =
        cache.loadBy(Unit).orEmpty()

    override fun cache(schedules: List<Schedule>) =
        cache.cacheBy(Unit, schedules)
}