package ru.rea.food

import android.annotation.SuppressLint
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

object HardwareId {
    @SuppressLint("HardwareIds")
    @Composable
    fun get(): String {
        return Settings.Secure.getString(
            LocalContext.current.contentResolver,
            Settings.Secure.ANDROID_ID
        )
    }
}