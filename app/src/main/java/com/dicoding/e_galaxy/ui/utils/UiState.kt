package com.dicoding.e_galaxy.ui.utils

sealed class UiState<out T: Any?> {
    object Loading : UiState<Nothing>()
    data class Success<out T: Any>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}
