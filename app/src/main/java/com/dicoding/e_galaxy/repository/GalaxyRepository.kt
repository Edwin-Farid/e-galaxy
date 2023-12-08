package com.dicoding.e_galaxy.repository

import com.dicoding.e_galaxy.data.model.Galaxy
import com.dicoding.e_galaxy.data.model.GalaxysData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class GalaxyRepository {
    private val dummyGalaxy = mutableListOf<Galaxy>()

    init {
        if(dummyGalaxy.isEmpty()){
            GalaxysData.dummyGalaxys.forEach{
                dummyGalaxy.add(it)
            }
        }
    }

    fun getGalaxyById(galaxyId: Int): Galaxy {
        return dummyGalaxy.first {
            it.id == galaxyId
        }
    }

    fun getBookmarkGalaxy(): Flow<List<Galaxy>> {
        return flowOf(dummyGalaxy.filter { it.isBookmark })
    }

    fun searchGalaxy(query: String) = flow {
        val data = dummyGalaxy.filter {
            it.name.contains(query, ignoreCase = true)
        }
        emit(data)
    }

    fun updateGalaxy(galaxyId: Int, newState: Boolean): Flow<Boolean> {
        val index = dummyGalaxy.indexOfFirst { it.id == galaxyId }
        val result = if (index >= 0) {
            val galaxy = dummyGalaxy[index]
            dummyGalaxy[index] = galaxy.copy(isBookmark = newState)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    companion object{
        @Volatile
        private var instance: GalaxyRepository? = null

        fun getInstance(): GalaxyRepository =
            instance ?: synchronized(this){
                GalaxyRepository().apply {
                    instance = this
                }
            }
    }
}