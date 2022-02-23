package ru.rea.food.vm

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.rea.food.Errors
import ru.rea.food.Place
import ru.rea.food.Profile
import ru.rea.food.REAFoodService

private const val TAG = "MainViewModel"

class MainViewModel : ViewModel() {
    var places by mutableStateOf<List<Place>>(listOf())
    var error by mutableStateOf(false)
    var profile by mutableStateOf<Profile?>(null)
    var errors by mutableStateOf<Errors?>(null)

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            try {
                places = REAFoodService.instance.places()
                error = false
            } catch (e: Exception) {
                error = true
            }
        }
    }

    fun profile(token: String): Profile? {
        viewModelScope.launch {
            try {
                profile = REAFoodService.instance.profile("Bearer $token")
            } catch (e: Exception) {
                error = true
                Log.e(TAG, "profile: load", e)
            }
        }
        return profile
    }

    fun changeName(token: String, name: String) {
        viewModelScope.launch {
            errors = REAFoodService.instance.changeName("Bearer $token", name).errors
            Log.d(TAG, "changeName: changed")
            profile(token)
        }
    }

    fun changeEmail(token: String, email: String) {
        viewModelScope.launch {
            errors = REAFoodService.instance.changeEmail("Bearer $token", email).errors
            Log.d(TAG, "changeEmail: $errors")
            profile(token)
        }
    }

    fun changePassword(token: String, password: String) {
        viewModelScope.launch {
            errors = REAFoodService.instance.changePassword("Bearer $token", password).errors
        }
    }
}