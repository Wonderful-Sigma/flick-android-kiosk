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
fun SnackList(navController: NavController, productList: List<ProductResponse>) {

    val drinkList = listOf("토레타", "블루하와이", "카프리썬", "코코팜", "포카리스웨트", "뽀로로 음료")

    VerticalGrid(
        modifier = Modifier.padding(horizontal = 33.dp),
        columns = SimpleGridCells.Fixed(3),
    ) {
        for (product in productList) {
            if (product.name in drinkList) {

            } else {
                ProductBox(
                    image = product.imageUrl,
                    price =  product.value,
                    name = product.name,
                    count = product.count
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
}