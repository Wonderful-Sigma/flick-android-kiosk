package com.staker4wapper.flick_kiosk.presentation.screen.product

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.staker4wapper.flick_kiosk.presentation.screen.home.HomeViewModel
import com.staker4wapper.flick_kiosk.presentation.ui.theme.BackArrowIconButton
import com.staker4wapper.flick_kiosk.presentation.ui.theme.Gray.gray400

@Composable
fun CreateProductScreen(
    navController: NavController, homeViewModel: HomeViewModel
) {

    var selectedUri: Uri? by remember {
        mutableStateOf(null)
    }

    val pickSingleMedia =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                Log.d("JWH", "Selected URI: $uri")
                selectedUri = uri
            } else {
                Log.d("JWH", "No media selected")
            }
        }

    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var nameIsFocused by remember { mutableStateOf(false) }
    var priceIsFocused by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val contentResolver = context.contentResolver

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(Color.White)
        ) {
            BackArrowIconButton(navController = navController)
        }
        Text(
            text = "추가할 상품을 입력해주세요",
            modifier = Modifier.padding(top = 20.dp, bottom = 50.dp),
            color = Color(0xFF1A1E27),
            fontSize = 32.sp
        )
        Box(
            modifier = Modifier
                .background(Color(0xFFF2F3F4), RoundedCornerShape(30.dp))
                .size(252.dp)
                .clickable {
                    pickSingleMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                }, contentAlignment = Alignment.Center
        ) {
            Text(text = "사진등록", color = gray400, fontSize = 24.sp)
            if (selectedUri != null) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(selectedUri)
                        .build(),
                    modifier = Modifier
                        .background(Color(0xFFF2F3F4), RoundedCornerShape(30.dp))
                        .size(252.dp),
                    contentDescription = "",

                    )
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
        BasicTextField(value = name,
            onValueChange = {
                if (it.length <= 10) {
                    name = it
                }
            },
            modifier = Modifier
                .height(61.dp)
                .width(448.dp)
                .background(Color.White, shape = RoundedCornerShape(size = 16.dp))
                .border(
                    width = 1.dp,
                    color = Color(0xFFDFDFDF),
                    shape = RoundedCornerShape(size = 16.dp)
                )
                .padding(start = 24.dp, end = 24.dp)
                .onFocusChanged {
                    nameIsFocused = it.isFocused
                },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text).copy(),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .width(100.dp)
                        .padding(start = 20.dp, end = 30.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (name.isEmpty() && !nameIsFocused) Text(
                        text = "상품의 이름을 입력해주세요", color = gray400, fontSize = 20.sp
                    )
                    innerTextField()
                }
            })
        Spacer(modifier = Modifier.height(13.dp))
        BasicTextField(value = price,
            onValueChange = {
                val filtered = it.filter { char -> char.isDigit() }
                if (filtered.length <= 10) {
                    price = filtered
                }
            },
            modifier = Modifier
                .height(61.dp)
                .width(448.dp)
                .background(Color.White, shape = RoundedCornerShape(size = 16.dp))
                .border(
                    width = 1.dp,
                    color = Color(0xFFDFDFDF),
                    shape = RoundedCornerShape(size = 16.dp)
                )
                .padding(start = 24.dp, end = 24.dp)
                .onFocusChanged {
                    priceIsFocused = it.isFocused
                },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number).copy(),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .width(100.dp)
                        .padding(start = 20.dp, end = 30.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (price.isEmpty() && !priceIsFocused) Text(
                        text = "가격을 입력해주세요 (ex. 200)", color = gray400, fontSize = 20.sp
                    )
                    innerTextField()
                }
            })
        Spacer(modifier = Modifier.height(72.dp))
        Box(
            Modifier
                .width(448.dp)
                .height(61.dp)
                .background(Color(0xFF5B73FF), shape = RoundedCornerShape(size = 16.dp))
                .clickable {
                    if (name.isNotEmpty() && price.isNotEmpty()) {
                        homeViewModel.createProduct(name, price.toInt(), selectedUri, contentResolver)
                    } else {
                        Toast.makeText(context, "내용을 채워주세요", Toast.LENGTH_SHORT).show()
                    }
                }, contentAlignment = Alignment.Center
        ) {
            Text(text = "상품 추가", color = Color.White, fontSize = 24.sp)
        }

        LaunchedEffect(homeViewModel.createProductState) {
            homeViewModel.createProductState.collect {
                if (it.isSuccess) {
                    navController.popBackStack()
                }
            }
        }
    }
}
