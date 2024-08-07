package com.staker4wapper.flick_kiosk.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.staker4wapper.flick_kiosk.presentation.screen.home.HomeScreen
import com.staker4wapper.flick_kiosk.presentation.screen.home.HomeViewModel
import com.staker4wapper.flick_kiosk.presentation.screen.product.CreateProductScreen
import com.staker4wapper.flick_kiosk.presentation.screen.qrcode.QRCodeScreen
import com.staker4wapper.flick_kiosk.presentation.screen.result.FailedScreen
import com.staker4wapper.flick_kiosk.presentation.screen.result.LoadScreen
import com.staker4wapper.flick_kiosk.presentation.screen.result.SuccessScreen
import newjeans.bunnies.data.DataManager

@Composable
fun NavGraph(
    navController: NavHostController, homeViewModel: HomeViewModel, dataManager: DataManager
) {
    NavHost(
        navController = navController, startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController, homeViewModel, dataManager)
        }
        composable(route = Screen.Create.route) {
            CreateProductScreen(navController = navController, homeViewModel = homeViewModel)
        }
        composable(route = Screen.QRCode.route, enterTransition = {
            when (initialState.destination.route) {
                Screen.Home.route -> slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )

                else -> null
            }
        }, popExitTransition = {
            when (targetState.destination.route) {
                Screen.Home.route -> slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(500)
                )

                else -> null
            }
        }) { backStackEntry ->
            val productPrice = backStackEntry.arguments?.getString("price")
            val productName = backStackEntry.arguments?.getString("name")

            if (productPrice != null && productName != null) {
                QRCodeScreen(navController, productPrice, productName)
            }
        }
        composable(route = Screen.Load.route, enterTransition = {
            when (initialState.destination.route) {
                Screen.QRCode.route -> slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )

                else -> null
            }
        }, popExitTransition = {
            when (targetState.destination.route) {
                Screen.QRCode.route -> slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(500)
                )

                else -> null
            }
        }/* ------------------- */) { backStackEntry ->
            val productPrice = backStackEntry.arguments?.getString("price")
            val sendUserId = backStackEntry.arguments?.getString("user_id")

            if (productPrice != null && sendUserId != null) {
                LoadScreen(navController, sendUserId, productPrice, dataManager)
            }
        }
        composable(
            route = Screen.Success.route,
            /* ------------------- */
        ) { backStackEntry ->
            val productPrice = backStackEntry.arguments?.getString("price")

            if (productPrice != null) {
                SuccessScreen(navController, productPrice)
            }
        }
        composable(
            route = Screen.Failed.route,
            /* ------------------- */
        ) {
            FailedScreen(navController = navController)
        }
    }
}