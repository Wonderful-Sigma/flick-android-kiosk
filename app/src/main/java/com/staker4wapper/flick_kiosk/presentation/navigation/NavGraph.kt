package com.staker4wapper.flick_kiosk.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.staker4wapper.flick_kiosk.presentation.screen.home.HomeScreen
import com.staker4wapper.flick_kiosk.presentation.screen.home.HomeViewModel
import com.staker4wapper.flick_kiosk.presentation.screen.qrcode.QRCodeScreen
import com.staker4wapper.flick_kiosk.presentation.screen.result.FailedScreen
import com.staker4wapper.flick_kiosk.presentation.screen.result.SuccessScreen

@Composable
fun NavGraph(
    navController : NavHostController,
    homeViewModel: HomeViewModel
){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController, homeViewModel)
        }
        composable(
            route = Screen.QRCode.route,
            enterTransition = {
                when (initialState.destination.route) {
                    Screen.Home.route ->
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Left,
                            animationSpec = tween(500)
                        )
                    else -> null
                }
            },
            popExitTransition = {
                when (targetState.destination.route) {
                    Screen.Home.route ->
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Right,
                            animationSpec = tween(500)
                        )

                    else -> null
                }
            }
            /* ------------------- */
        ){ backStackEntry ->
            val productPrice = backStackEntry.arguments?.getString("price")
            val productName = backStackEntry.arguments?.getString("name")

            if (productPrice != null && productName != null) {
                QRCodeScreen(navController, productPrice, productName)
            }
        }
        composable(
            route = Screen.Success.route,
            enterTransition = {
                when (initialState.destination.route) {
                    Screen.QRCode.route ->
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Left,
                            animationSpec = tween(500)
                        )
                    else -> null
                }
            },
            popExitTransition = {
                when (targetState.destination.route) {
                    Screen.QRCode.route ->
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Right,
                            animationSpec = tween(500)
                        )

                    else -> null
                }
            }
            /* ------------------- */
        ) { backStackEntry ->
            val productPrice = backStackEntry.arguments?.getString("price")

            if (productPrice != null) {
                SuccessScreen(navController, productPrice)
            }
        }
        composable(
            route = Screen.Failed.route,
            enterTransition = {
                when (initialState.destination.route) {
                    Screen.QRCode.route ->
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Left,
                            animationSpec = tween(500)
                        )
                    else -> null
                }
            },
            popExitTransition = {
                when (targetState.destination.route) {
                    Screen.QRCode.route ->
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Right,
                            animationSpec = tween(500)
                        )

                    else -> null
                }
            }
            /* ------------------- */
        ) {
            FailedScreen(navController = navController)
        }
    }
}