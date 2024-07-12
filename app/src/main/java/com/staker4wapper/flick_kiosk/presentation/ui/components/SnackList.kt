package com.staker4wapper.flick_kiosk.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.staker4wapper.flick_kiosk.data.room.Product
import com.staker4wapper.flick_kiosk.presentation.navigation.Screen
import com.staker4wapper.flick_kiosk.presentation.screen.home.HomeViewModel
import io.woong.compose.grid.SimpleGridCells
import io.woong.compose.grid.VerticalGrid

@Composable
fun SnackList(
    navController: NavController,
    productList: List<Product>,
    isAdmin: Boolean,
    homeViewModel: HomeViewModel
) {

    VerticalGrid(
        modifier = Modifier.padding(horizontal = 33.dp),
        columns = SimpleGridCells.Fixed(3),
    ) {
        for (product in productList) {
            ProductBox(
                uid = product.uid,
                image = product.image,
                price = product.price,
                name = product.name,
                isAdmin = isAdmin,
                homeViewModel = homeViewModel
            ) {
                navController.navigate(
                    Screen.QRCode.route
                        .replace(
                            oldValue = "{price}",
                            newValue = product.price.toString()
                        )
                        .replace(
                            oldValue = "{name}",
                            newValue = product.name
                        )
                )
            }
        }
    }
}