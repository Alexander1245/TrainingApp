package com.dart69.trainingapp.trainings.data.datasource

import com.dart69.trainingapp.trainings.data.models.SchedulesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SchedulesApi {
    @GET(END_POINT)
    suspend fun loadSchedules(
        @Query(CLUB_ID) clubId: Int = DEFAULT_CLUB,
    ): Response<SchedulesResponse>

    private companion object {
        const val END_POINT = "schedule/get_v3/"
        const val CLUB_ID = "club_id"
        const val DEFAULT_CLUB = 2
    }
}