package com.staker4wapper.flick_kiosk.presentation.screen.home

import android.util.Log
import android.widget.Toast
import com.staker4wapper.flick_kiosk.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.staker4wapper.flick_kiosk.presentation.navigation.Screen
import com.staker4wapper.flick_kiosk.presentation.ui.components.SnackList
import com.staker4wapper.flick_kiosk.presentation.ui.theme.FlickIcon
import com.staker4wapper.flick_kiosk.presentation.ui.theme.Gray
import com.staker4wapper.flick_kiosk.presentation.ui.theme.Gray.gray100
import com.staker4wapper.flick_kiosk.presentation.ui.theme.Gray.gray400
import com.staker4wapper.flick_kiosk.presentation.ui.theme.SubTitleLarge
import com.staker4wapper.flick_kiosk.presentation.ui.theme.TitleLarge

const val PASSWORD = "1234" // TODO : 비밀번호 수정하기

@Composable
fun HomeScreen(
    navController: NavController, homeViewModel: HomeViewModel
) {
    val productList = homeViewModel.productList.observeAsState()
    val scrollState = rememberLazyListState()

    val state = homeViewModel.state.observeAsState(false)

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AdminPasswordDialog(onConfirm = {
            showDialog = false
            homeViewModel.changeState() // state 변경
            Log.d("onConfirm", "HomeScreen: $state")
        }, onDismiss = {
            showDialog = false
            Log.d("onDismiss", "HomeScreen: $state")
        })
    }

    Scaffold(topBar = {
        Row(
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 45.dp)
                .height(80.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (state.value) { SubTitleLarge(text = "총 결재된 금액 : 1,000대소코인", color = gray400) }
            Spacer(modifier = Modifier.weight(1F))
            Row(modifier = Modifier
                .clickable {
                    // TODO : 상태가 클라이언트 상태일 때만 (false)
                    if (homeViewModel.state.value == null || homeViewModel.state.value == false) {
                        showDialog = true
                    } else {
                        homeViewModel.changeState()
                    }
                }
                .background(gray100, RoundedCornerShape(8.dp))
                .height(37.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_change),
                    contentDescription = "",
                    modifier = Modifier.padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = if (state.value) "어드민 변경" else "클라이언트 변경",
                    color = Color(0xFF1A1E27),
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
        }
    }) {
        LazyColumn(
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 45.dp)
                .fillMaxSize()
                .padding(it),
            state = scrollState
        ) {
            item {
                Row(Modifier.fillMaxWidth()) {
                    TitleLarge(
                        text = "구매할 상품을 선택해주세요",
                        color = Gray.gray900
                    )
                    Spacer(Modifier.weight(1F))
                    if (state.value) {
                        Box(modifier = Modifier
                            .clickable { navController.navigate(Screen.Create.route) }
                            .background(Color(0xFF5B73FF), RoundedCornerShape(8.dp))
                            .height(37.dp), contentAlignment = Alignment.Center) {
                            Text(
                                text = "상품 추가하기",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(52.dp))
            }
            item {
                productList.value?.let {
                    SnackList(navController = navController, productList = it)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController()
    val homeViewModel: HomeViewModel = hiltViewModel()
    HomeScreen(navController, homeViewModel)
}

@Composable
fun AdminPasswordDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                if (password == PASSWORD) {
                    onConfirm()
                } else {
                    Toast.makeText(context, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("확인", color = Color.Black, fontSize = 16.sp)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("취소", color = Color.Gray, fontSize = 16.sp)
            }
        },
        title = {
            Text("어드민으로 변경하기 위해\n비밀번호를 입력해주세요", fontSize = 18.sp, color= Color.Black)
        },
        text = {
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("비밀번호를 입력해주세요") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        },
        modifier = Modifier
            .padding(16.dp)
            .background(Color.White, RoundedCornerShape(20.dp))
    )
}