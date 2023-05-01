package com.dart69.trainingapp.trainings.data.models

class ApiError(
    private val errorCode: Int,
    override val message: String? = "Api error is occurred: error code $errorCode"
) : IllegalStateException()