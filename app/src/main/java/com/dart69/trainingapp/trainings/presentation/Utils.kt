package com.dart69.trainingapp.trainings.presentation

import android.os.Build
import android.provider.Settings
import com.dart69.core.errors.NetworkTimeOutError
import com.dart69.mvvm.events.*
import com.dart69.mvvm.strings.asStringResource
import com.dart69.trainingapp.R
import com.faltenreich.skeletonlayout.Skeleton
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*

var Skeleton.isSkeletonVisible: Boolean
    get() = isSkeleton()
    set(isVisible) = if (isVisible) showSkeleton() else showOriginal()

fun buildNetworkDialog(onClick: () -> Unit): BaseShowDialog =
    ShowCommonDialog(
        title = R.string.network_is_disabled.asStringResource(),
        message = R.string.are_you_want_to_enable_network.asStringResource(),
        positiveButton = Button(text = R.string.yes.asStringResource(), onClick),
    )

fun buildErrorSnackBar(throwable: Throwable): BaseShowSnackBar =
    ShowCommonSnackBar(
        message = when {
            throwable is NetworkTimeOutError -> R.string.network_timeout.asStringResource()
            throwable.message != null -> R.string.error_1s.asStringResource(throwable.message!!)
            else -> R.string.internal_error.asStringResource()
        }
    )

fun provideNetworkSettingsIntent(): String =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        Settings.Panel.ACTION_INTERNET_CONNECTIVITY
    } else {
        Settings.ACTION_WIRELESS_SETTINGS
    }

fun String.formatToTime(): Long {
    val date = LocalDate.parse(this)
    return date.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()
}

fun Long.toDateString(pattern: String = "EEEE, dd MMMM"): String {
    val dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(this), ZoneId.systemDefault())
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    return dateTime.format(formatter)
}