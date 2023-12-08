package com.dicoding.e_galaxy.ui.screen.Home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.e_galaxy.data.model.Galaxy
import com.dicoding.e_galaxy.repository.GalaxyRepository
import com.dicoding.e_galaxy.ui.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: GalaxyRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Galaxy>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Galaxy>>>
        get() = _uiState

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) = viewModelScope.launch {
        _query.value = newQuery
        repository.searchGalaxy(_query.value)
            .catch {
                _uiState.value = UiState.Error(it.message.toString())
            }
            .collect{
                _uiState.value = UiState.Success(it)
            }
    }

    fun updateGalaxy(id: Int, newState: Boolean) = viewModelScope.launch {
        repository.updateGalaxy(id, newState)
            .collect{isUpdated ->
                if (isUpdated) search(_query.value)
            }
    }
}