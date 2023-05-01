package com.dart69.trainingapp.trainings.data.datasource

import com.dart69.core.mapper.Mapper
import com.dart69.data.response.wrapper.wrapResponseErrors
import com.dart69.trainingapp.trainings.data.models.ApiError
import retrofit2.Response

abstract class BaseRemoteDataSource(
    private val errorCodeMapper: Mapper<Int, Throwable> = Mapper { errorCode -> ApiError(errorCode) },
) {

    protected suspend fun <T> wrapResponse(block: suspend () -> Response<T>): T =
        wrapResponseErrors(errorCodeMapper, block)
}