package ru.rea.food

import android.annotation.SuppressLint
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@SuppressLint("HardwareIds")
@Composable
fun getAndroidId(): String {
    return Settings.Secure.getString(
        LocalContext.current.contentResolver,
        Settings.Secure.ANDROID_ID
    )
}