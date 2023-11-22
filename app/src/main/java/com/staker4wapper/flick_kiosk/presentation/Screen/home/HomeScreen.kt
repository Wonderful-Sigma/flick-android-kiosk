package com.staker4wapper.flick_kiosk.presentation.Screen.home

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat.getColor
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.staker4wapper.flick_kiosk.R
import com.staker4wapper.flick_kiosk.presentation.Screen.qrcode.QRViewModel
import com.staker4wapper.flick_kiosk.presentation.navigation.Screen
import com.staker4wapper.flick_kiosk.presentation.ui.components.ProductBox
import com.staker4wapper.flick_kiosk.presentation.ui.theme.Gray
import com.staker4wapper.flick_kiosk.presentation.ui.theme.TitleLarge

@Composable
fun HomeScreen(
    navController: NavController
) {

    val productList = listOf(
        Product(price = 358, name = "아주 맛있는 코코팜1"),
        Product(price = 368, name = "아주 맛있는 코코팜2"),
        Product(price = 378, name = "아주 맛있는 코코팜3"),
        Product(price = 388, name = "아주 맛있는 코코팜4"),
    )

    val homeViewModel: HomeViewModel = hiltViewModel()
//    homeViewModel.getAllProducts() // todo : 이거 형식 수정 필요할 듯

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        TitleLarge(
            modifier = Modifier.padding(start = 45.dp),
            text = "구매할 상품을 선택해주세요",
            color = Gray.gray900
        )
        Spacer(modifier = Modifier.height(40.dp))
        ProductList(
            navController = navController,
            productList = productList
        )
    }
}

@Composable
fun ProductList(navController: NavController, productList: List<Product>) {
    LazyColumn {
        items(productList.size) {
            ProductBox(price =  productList[it].price, name = productList[it].name) {
                navController.navigate(
                    Screen.QRCode.route
                        .replace(
                            oldValue = "{price}",
                            newValue =  productList[it].price.toString()
                        )
                        .replace(
                            oldValue = "{name}",
                            newValue = productList[it].name
                        )
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}

