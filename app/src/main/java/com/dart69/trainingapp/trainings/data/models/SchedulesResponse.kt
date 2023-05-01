package com.dart69.trainingapp.trainings.data.models

data class SchedulesResponse(
    val lessons: List<LessonDto>,
    val option: OptionDto,
    val tabs: List<TabDto>,
    val trainers: List<TrainerDto>
)