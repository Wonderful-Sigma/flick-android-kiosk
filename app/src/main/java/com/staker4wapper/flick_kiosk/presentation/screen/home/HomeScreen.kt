package com.staker4wapper.flick_kiosk.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.staker4wapper.flick_kiosk.R
import com.staker4wapper.flick_kiosk.presentation.ui.components.ProductList
import com.staker4wapper.flick_kiosk.presentation.ui.theme.FlickIcon
import com.staker4wapper.flick_kiosk.presentation.ui.theme.Gray
import com.staker4wapper.flick_kiosk.presentation.ui.theme.TitleLarge
import com.staker4wapper.flick_kiosk.presentation.ui.theme.TitleSmall

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel
) {
    val productList = homeViewModel.productList.observeAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        TitleLarge(
            modifier = Modifier.padding(start = 45.dp),
            text = "구매할 상품을 선택해주세요",
            color = Gray.gray900
        )
        TitleSmall(
            modifier = Modifier.padding(top = 60.dp, start = 45.dp),
            text = "간식",
            color = Gray.gray700
        )
        Spacer(modifier = Modifier.height(30.dp))
        productList.value?.let {
            ProductList(navController = navController, productList = it)
        }
        Spacer(modifier = Modifier.height(80.dp))
        FlickIcon()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController()
    val homeViewModel: HomeViewModel = hiltViewModel()
    HomeScreen(navController, homeViewModel)
}

