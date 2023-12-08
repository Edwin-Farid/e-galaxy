package com.dicoding.e_galaxy.ui.screen.Detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.e_galaxy.data.model.Galaxy
import com.dicoding.e_galaxy.repository.GalaxyRepository
import com.dicoding.e_galaxy.ui.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: GalaxyRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Galaxy>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Galaxy>>
        get() = _uiState

    fun getGalaxyById(id: Int) = viewModelScope.launch {
        _uiState.value = UiState.Loading
        _uiState.value = UiState.Success(repository.getGalaxyById(id))
    }

    fun updateNews(id: Int, newState: Boolean) = viewModelScope.launch {
        repository.updateGalaxy(id, !newState)
            .collect{ isUpdated ->
                if(isUpdated) getGalaxyById(id)
            }
    }

}