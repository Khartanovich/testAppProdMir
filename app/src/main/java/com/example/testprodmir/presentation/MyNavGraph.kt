package com.example.testprodmir.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.testprodmir.Constans

@Composable
fun MyNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Constans.ROUTE_FIRST) {
        composable(Constans.ROUTE_FIRST) {
            ScreenAuthoriz1(navController = navController)
        }
        composable(
            "${Constans.ROUTE_SECOND}/{${Constans.PHONE}}",
            arguments = listOf(navArgument(Constans.PHONE) {
                type = NavType.StringType
            })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString(Constans.PHONE)?.let {
                ScreenAuthoriz2(phoneNumber = it, navController = navController)
            }
        }
        composable("${Constans.ROUTE_MAIN}/{${Constans.TOKEN}}",
            arguments = listOf(navArgument(Constans.TOKEN) {
                type = NavType.StringType
            })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString(Constans.TOKEN)?.let {
                MainScreen(text = it)
            }
        }
    }
}