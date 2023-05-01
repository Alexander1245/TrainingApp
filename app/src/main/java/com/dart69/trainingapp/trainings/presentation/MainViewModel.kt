package com.dart69.trainingapp.trainings.presentation

import androidx.lifecycle.viewModelScope
import com.dart69.core.errors.NoNetworkError
import com.dart69.core.mapper.Mapper
import com.dart69.core.results.ResultsMutableStateFlow
import com.dart69.core.results.isLoading
import com.dart69.core.results.onError
import com.dart69.core.results.takeData
import com.dart69.mvvm.viewmodels.CommunicatorViewModel
import com.dart69.trainingapp.trainings.domain.SchedulesRepository
import com.dart69.trainingapp.trainings.domain.SchedulesRepository.Options
import com.dart69.trainingapp.trainings.domain.models.Schedule
import com.dart69.trainingapp.trainings.presentation.models.SchedulesItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: SchedulesRepository,
    private val mapper: Mapper<List<@JvmSuppressWildcards Schedule>, List<@JvmSuppressWildcards SchedulesItem>>
) : CommunicatorViewModel<MainViewModel.MainState, MainEvents>(MainState()) {
    private val schedules = ResultsMutableStateFlow<List<Schedule>>(emptyList())

    init {
        collectSchedules()
        refresh()
    }

    fun refresh() {
        viewModelScope.launch { schedules.emitAll(repository.loadSchedules(Options.FORCE_REFRESH)) }
    }

    private fun collectSchedules() {
        schedules.onEach { results ->
            states.update { current ->
                current.copy(
                    items = results.takeData()?.let(mapper::map) ?: current.items,
                    isLoading = results.isLoading(),
                )
            }
        }.onError(this::handleError).launchIn(viewModelScope)
    }

    private suspend fun handleError(throwable: Throwable) {
        events.emit(
            if (throwable is NoNetworkError) {
                MainEvents.DisplayNetworkErrorDialog(this::openNetworkSettings)
            } else {
                MainEvents.DisplayErrorMessage(throwable)
            }
        )
    }

    private fun openNetworkSettings() = events.launch(MainEvents.OpenNetworkSettings)

    data class MainState(
        val items: List<SchedulesItem> = emptyList(),
        val isLoading: Boolean = false,
    )
}