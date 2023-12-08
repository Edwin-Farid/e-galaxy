package com.dicoding.e_galaxy.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.e_galaxy.repository.GalaxyRepository
import com.dicoding.e_galaxy.ui.screen.Bookmark.BookmarkViewModel
import com.dicoding.e_galaxy.ui.screen.Detail.DetailViewModel
import com.dicoding.e_galaxy.ui.screen.Home.HomeViewModel

class ViewModelFactory(private val repository: GalaxyRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(BookmarkViewModel::class.java)){
            return BookmarkViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class: " +modelClass.name)
    }
}