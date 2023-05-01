package com.dart69.trainingapp.trainings.data

import com.dart69.core.coroutines.DispatchersProvider
import com.dart69.core.results.ResultsFlow
import com.dart69.core.results.resultsFlowOf
import com.dart69.trainingapp.trainings.data.datasource.SchedulesDataSource
import com.dart69.trainingapp.trainings.domain.SchedulesRepository
import com.dart69.trainingapp.trainings.domain.models.Schedule
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SchedulesRepositoryImpl @Inject constructor(
    private val dispatchersProvider: DispatchersProvider,
    private val remoteDataSource: SchedulesDataSource.Remote,
    private val cachedDataSource: SchedulesDataSource.Cached,
) : SchedulesRepository {
    override fun loadSchedules(
        options: SchedulesRepository.Options
    ): ResultsFlow<List<Schedule>> = resultsFlowOf {
        when (options) {
            SchedulesRepository.Options.LOAD_FROM_CACHE -> loadFromCacheIfNotEmpty()
            SchedulesRepository.Options.FORCE_REFRESH -> refreshAndCache()
        }
    }.flowOn(dispatchersProvider.provideBackgroundDispatcher())

    private suspend fun refreshAndCache(): List<Schedule> =
        remoteDataSource.loadSchedules().also(cachedDataSource::cache)

    private suspend fun loadFromCacheIfNotEmpty(): List<Schedule> =
        cachedDataSource.loadSchedules().ifEmpty { refreshAndCache() }
}