package com.dart69.trainingapp.trainings.domain.models

data class Schedule(
    val id: Int,
    val startTime: String,
    val endTime: String,
    val name: String,
    val trainer: String,
    val date: Long,
    val location: String,
    val color: Int,
)
