package com.dicoding.e_galaxy.data

import com.dicoding.e_galaxy.repository.GalaxyRepository

object Injection {
    fun provideRepository(): GalaxyRepository{
        return GalaxyRepository.getInstance()
    }
}