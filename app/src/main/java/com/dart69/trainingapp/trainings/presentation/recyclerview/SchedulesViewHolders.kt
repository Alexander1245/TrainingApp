package com.dart69.trainingapp.trainings.presentation.recyclerview

import com.dart69.trainingapp.databinding.ItemDateBinding
import com.dart69.trainingapp.databinding.ItemScheduleBinding
import com.dart69.trainingapp.trainings.presentation.models.SchedulesItem

class SchedulesViewHolder(
    private val binding: ItemScheduleBinding
) : BaseViewHolder<SchedulesItem, ItemScheduleBinding>(binding) {
    override fun bind(item: SchedulesItem) = binding.run {
        require(item is SchedulesItem.Common)
        val schedule = item.schedule
        divider.dividerColor = schedule.color
        textViewLocation.text = schedule.location
        textViewTrainer.text = schedule.trainer
        textViewType.text = schedule.name
        textViewTimeStart.text = schedule.startTime
        textViewTimeEnd.text = schedule.endTime
    }
}

class DateViewHolder(
    private val binding: ItemDateBinding
) : BaseViewHolder<SchedulesItem, ItemDateBinding>(binding) {
    override fun bind(item: SchedulesItem) {
        require(item is SchedulesItem.Date)
        binding.textViewDate.text = item.date
    }
}