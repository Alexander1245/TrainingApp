package com.dart69.trainingapp.trainings.presentation.recyclerview

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<T : Any, VB : ViewBinding>(
    binding: VB,
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: T)
}

val View.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(context)