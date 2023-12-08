package com.dicoding.e_galaxy.data.model

data class Galaxy(
    val id: Int,
    val name: String,
    val originName: String,
    val photo: Int,
    val distanceDiameter: String,
    val linksGalaxy: String,
    var isBookmark: Boolean = false
)
