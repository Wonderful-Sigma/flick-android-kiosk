package com.staker4wapper.flick_kiosk.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.staker4wapper.flick_kiosk.data.dto.ProductResponse
import com.staker4wapper.flick_kiosk.presentation.navigation.Screen
import io.woong.compose.grid.SimpleGridCells
import io.woong.compose.grid.VerticalGrid

@Composable
fun ProductList(navController: NavController, productList: List<ProductResponse>) {
    VerticalGrid(
        modifier = Modifier.padding(horizontal = 33.dp),
        columns = SimpleGridCells.Fixed(3),
    ) {
        for (product in productList) {
            ProductBox(
                image = product.imageUrl,
                price =  product.value,
                name = product.name
            ) {
                navController.navigate(
                    Screen.QRCode.route
                        .replace(
                            oldValue = "{price}",
                            newValue =  product.value.toString()
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