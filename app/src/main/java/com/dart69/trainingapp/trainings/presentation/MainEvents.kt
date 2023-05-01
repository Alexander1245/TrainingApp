package com.dart69.trainingapp.trainings.presentation

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.dart69.mvvm.events.ContextEvent
import com.dart69.mvvm.events.Event
import com.dart69.mvvm.events.ViewEvent

sealed class MainEvents : Event {
    data class DisplayErrorMessage(
        private val throwable: Throwable
    ) : MainEvents(), ViewEvent by buildErrorSnackBar(throwable)

    data class DisplayNetworkErrorDialog(
        private val onClick: () -> Unit,
    ) : MainEvents(), ContextEvent by buildNetworkDialog(onClick)

    object OpenNetworkSettings : MainEvents() {
        fun applyOn(launcher: ActivityResultLauncher<Intent>) {
            launcher.launch(Intent(provideNetworkSettingsIntent()))
        }
    }
}
