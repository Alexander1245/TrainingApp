package com.dart69.trainingapp.trainings.di

import com.dart69.core.mapper.Mapper
import com.dart69.trainingapp.trainings.data.SchedulesMapper
import com.dart69.trainingapp.trainings.data.SchedulesRepositoryImpl
import com.dart69.trainingapp.trainings.data.datasource.SchedulesDataSource
import com.dart69.trainingapp.trainings.data.datasource.SchedulesRemoteDataSource
import com.dart69.trainingapp.trainings.data.models.SchedulesResponse
import com.dart69.trainingapp.trainings.domain.SchedulesRepository
import com.dart69.trainingapp.trainings.domain.models.Schedule
import com.dart69.trainingapp.trainings.presentation.SchedulesItemMapper
import com.dart69.trainingapp.trainings.presentation.models.SchedulesItem
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppBinders {

    @Binds
    fun bindSchedulesMapper(
        mapper: SchedulesMapper
    ): Mapper<SchedulesResponse, List<@JvmSuppressWildcards Schedule>>

    @Binds
    fun bindRemoteDateSource(
        dataSource: SchedulesRemoteDataSource
    ): SchedulesDataSource.Remote

    @Binds
    fun bindRepository(
        repositoryImpl: SchedulesRepositoryImpl
    ): SchedulesRepository

    @Binds
    fun bindSchedulesItemMapper(
        mapper: SchedulesItemMapper
    ): Mapper<List<@JvmSuppressWildcards Schedule>, List< @JvmSuppressWildcards SchedulesItem>>
}