package ru.rea.food

import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun getAndroidId(): String {
    return Settings.Secure.getString(LocalContext.current.contentResolver, Settings.Secure.ANDROID_ID)
}