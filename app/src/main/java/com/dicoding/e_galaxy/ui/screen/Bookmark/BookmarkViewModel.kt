package com.dicoding.e_galaxy.ui.screen.Bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.e_galaxy.data.model.Galaxy
import com.dicoding.e_galaxy.repository.GalaxyRepository
import com.dicoding.e_galaxy.ui.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class BookmarkViewModel(private val repository: GalaxyRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Galaxy>>> = MutableStateFlow(UiState.Loading)
    val  uiState: StateFlow<UiState<List<Galaxy>>>
        get() = _uiState

    fun getBookmarkNews() = viewModelScope.launch {
        repository.getBookmarkGalaxy()
            .catch {
                _uiState.value = UiState.Error(it.message.toString())
            }
            .collect{
                _uiState.value = UiState.Success(it)
            }
    }

    fun updateGalaxy(id: Int, newState: Boolean){
        repository.updateGalaxy(id, newState)
        getBookmarkNews()
    }
}