package com.dart69.trainingapp.trainings.di

import com.dart69.core.coroutines.DispatchersProvider
import com.dart69.trainingapp.trainings.data.datasource.SchedulesApi
import com.dart69.trainingapp.trainings.data.datasource.SchedulesCachedDataSource
import com.dart69.trainingapp.trainings.data.datasource.SchedulesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppSingletonProviders {
    private const val BASE_URL = "https://olimpia.fitnesskit-admin.ru/"

    @Provides
    fun provideDispatchers(): DispatchersProvider = DispatchersProvider()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideSchedulesApi(retrofit: Retrofit): SchedulesApi = retrofit.create()

    @Provides
    @Singleton
    fun provideCachedDataSource(): SchedulesDataSource.Cached = SchedulesCachedDataSource()
}