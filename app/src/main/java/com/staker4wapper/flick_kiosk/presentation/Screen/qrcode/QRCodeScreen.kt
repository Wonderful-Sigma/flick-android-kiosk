package com.staker4wapper.flick_kiosk.presentation.Screen.qrcode

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.dynamsoft.dbr.BarcodeReader
import com.dynamsoft.dbr.BarcodeReaderException
import com.dynamsoft.dce.CameraEnhancer
import com.dynamsoft.dce.DCECameraView
import com.dynamsoft.dce.EnumCameraPosition
import com.staker4wapper.flick_kiosk.R
import com.staker4wapper.flick_kiosk.data.dto.RemitRequest
import com.staker4wapper.flick_kiosk.presentation.findActivity
import com.staker4wapper.flick_kiosk.presentation.ui.components.BackArrowIconButtonForQRView
import com.staker4wapper.flick_kiosk.presentation.ui.theme.Gray
import com.staker4wapper.flick_kiosk.presentation.ui.theme.SubTitleLarge
import com.staker4wapper.flick_kiosk.presentation.ui.theme.SubTitleMedium
import com.staker4wapper.flick_kiosk.presentation.ui.theme.TitleLarge
import com.staker4wapper.flick_kiosk.presentation.ui.theme.TitleMedium
import dagger.hilt.android.AndroidEntryPoint

@Composable
fun QRCodeScreen(
    navController: NavController,
) {
    val qrViewModel: QRViewModel = hiltViewModel()

    lateinit var mCameraEnhancer: CameraEnhancer
    val mBarcodeReader = BarcodeReader()

    val context = LocalContext.current

    var barcodeTextResult by remember {
        mutableStateOf("")
    }

    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            hasCameraPermission = granted
            if (granted) {
                try {
                    mBarcodeReader.setTextResultListener { _, _, textResults ->
                        if (textResults.isNotEmpty()) {
                            val result = textResults[0]
                            // TODO : QR코드 인식이 되었을 때!!!
                            qrViewModel.decodingQrCode(result.barcodeText)
                            mBarcodeReader.stopScanning()
                            barcodeTextResult = "인식되었어요! : " + result.barcodeText
                        }
                    }
                    mBarcodeReader.startScanning()
                } catch (e: BarcodeReaderException) {
                    e.printStackTrace()
                }
                mCameraEnhancer.open()
            }
        }
    )

    LaunchedEffect(true){
        initLicense()
        try {
            mBarcodeReader.setCameraEnhancer(mCameraEnhancer)
        } catch (e: BarcodeReaderException){
            e.printStackTrace()
        }
        launcher.launch(Manifest.permission.CAMERA)

        qrViewModel.qrDecodingState.collect {
            if (it.isSuccess) {
                Toast.makeText(context, "성공했어요!", Toast.LENGTH_SHORT).show()
            }
            if (it.error.isNotEmpty()) {
                Toast.makeText(context, "실패했어요.. : " + it.error, Toast.LENGTH_SHORT).show()
            }
        }
        qrViewModel.sendUserInfo.observe() {
            val sendUserAccount = qrViewModel.sendUserInfo.value!!.id.toInt()
            qrViewModel.remit(
                RemitRequest(sendUserAccount, 10, 1)
            )
        }
    }

    /* TODO : View */

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        BackArrowIconButtonForQRView {
//            navController.popBackStack() // TODO : 뒤로가기
        }
        TitleLarge(
            modifier = Modifier.padding(start = 45.dp),
            text = "QR코드로 결제해주세요",
            color = Gray.gray900
        )
        SubTitleMedium(
            modifier = Modifier.padding(start = 45.dp, top = 12.dp),
            text = "결제 시 학생회 계좌로 돈이 자동으로 들어가져요",
            color = Gray.gray200
        )
        /* todo QR Code */
        Surface(
            modifier = Modifier.run {
                padding(horizontal = 45.dp)
                    .fillMaxWidth()
                    .height(800.dp)
                    .padding(top = 40.dp)
                    .clip(RoundedCornerShape(20.dp))
            },
            color = Color.White
        ) {
            AndroidView(factory = {context ->
                mCameraEnhancer = CameraEnhancer(context.findActivity())
                val mCameraView = DCECameraView(context)
                mCameraView.overlayVisible = true
                mCameraEnhancer.cameraView = mCameraView
                mCameraEnhancer.selectCamera(EnumCameraPosition.CP_FRONT)
                mCameraView
            })
            BarcodeText(text = barcodeTextResult)
        }
        /***/
        Row(
            modifier = Modifier
                .padding(horizontal = 45.dp)
                .padding(top = 20.dp)
        ) {
            Column {
                SubTitleLarge(text = "아주 맛있는 코코팜 (수정)", color = Gray.gray700)
                TitleLarge(modifier = Modifier.padding(top = 6.dp), text = "308코인 (수정)", color = Gray.gray700)
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.clickable {
                    mBarcodeReader.startScanning()
                    barcodeTextResult = ""
                }
            ) {
                SubTitleMedium(text = "다시 스캔하기", color = Gray.gray700)
                Icon(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(30.dp),
                    painter = painterResource(id = R.drawable.ic_reload),
                    contentDescription = "icReload"
                )
            }
        }
    }
}

data class QRResult(
    val barcodeTextResult: String,
    val launcher: ManagedActivityResultLauncher<String, Boolean>
)
@Composable
fun BarcodeText(text:String) {
    TitleMedium(
        modifier = Modifier.padding(30.dp),
        text = text,
        color = Color.White
    )
}

private fun initLicense(){
    BarcodeReader.initLicense(
        "DLS2eyJoYW5kc2hha2VDb2RlIjoiMjAwMDAxLTE2NDk4Mjk3OTI2MzUiLCJvcmdhbml6YXRpb25JRCI6IjIwMDAwMSIsInNlc3Npb25QYXNzd29yZCI6IndTcGR6Vm05WDJrcEQ5YUoifQ=="
    ) { isSuccess, error ->
        if (!isSuccess) {
            error.printStackTrace()
        }else{
            Log.d("DBR","license initialized")
        }
    }
}