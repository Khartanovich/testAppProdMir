package com.example.testprodmir.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.testprodmir.Constans

@Composable
fun MyNavGraph(
    navController: NavHostController,
    viewModel: MyVieModel = hiltViewModel(),
    startDestination: String
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Constans.ROUTE_FIRST) {
            ScreenAuthoriz1(navController = navController, viewModel = viewModel)
        }
        composable(
            "${Constans.ROUTE_SECOND}/{${Constans.PHONE}}",
            arguments = listOf(navArgument(Constans.PHONE) {
                type = NavType.StringType
            })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString(Constans.PHONE)?.let {
                ScreenAuthoriz2(
                    phoneNumber = it,
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
        composable(Constans.ROUTE_MAIN){
            MainScreen(navController = navController, viewModel = viewModel)
        }
    }
}