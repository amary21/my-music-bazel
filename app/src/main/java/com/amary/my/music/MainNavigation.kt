package com.amary.my.music

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.amary.my.music.feature.detail.detailScreen
import com.amary.my.music.feature.list.ListRoute
import com.amary.my.music.feature.list.listScreen

@Composable
fun MainNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ListRoute,
        modifier = modifier
    ) {
        listScreen(navController)
        detailScreen(navController)
    }
}