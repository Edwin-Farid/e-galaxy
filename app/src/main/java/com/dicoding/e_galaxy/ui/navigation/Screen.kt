package com.dicoding.e_galaxy.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Bookmark : Screen("bookmark")
    object Profile : Screen("profile")
    object DetailGalaxy : Screen("home/{galaxyId}"){
        fun createRoute(galaxyId: Int) = "home/$galaxyId"
    }
}
