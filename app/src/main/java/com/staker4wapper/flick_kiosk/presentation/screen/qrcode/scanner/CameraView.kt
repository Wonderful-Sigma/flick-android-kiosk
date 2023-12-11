package com.staker4wapper.flick_kiosk.presentation.screen.qrcode.scanner

import android.annotation.SuppressLint
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.common.util.concurrent.ListenableFuture
import com.staker4wapper.flick_kiosk.data.dto.QrDecodingResponse
import com.staker4wapper.flick_kiosk.data.dto.RemitRequest
import com.staker4wapper.flick_kiosk.presentation.navigation.Screen
import com.staker4wapper.flick_kiosk.presentation.screen.qrcode.QRViewModel
import kotlinx.coroutines.delay
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@SuppressLint("RestrictedApi", "VisibleForTests")
@Composable
fun CameraView(
    productPrice: Long,
    qrViewModel: QRViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var preview by remember { mutableStateOf<Preview?>(null) }
    val barCodeVal = remember { mutableStateOf("") }
    lateinit var cameraProvider: ProcessCameraProvider

    Surface(
        modifier = Modifier.run {
            padding(horizontal = 45.dp)
                .fillMaxWidth()
                .height(600.dp)
                .padding(top = 40.dp)
                .padding(horizontal = 100.dp)
                .clip(RoundedCornerShape(20.dp))
        },
        color = Color.White
    ) {
        AndroidView(
            factory = { AndroidViewContext ->
                PreviewView(AndroidViewContext).apply {
                    this.scaleType = PreviewView.ScaleType.FILL_CENTER
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                    )
                    implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                }
            },
            update = { previewView ->
                val cameraSelector: CameraSelector = CameraSelector.Builder()
                    .requireLensFacing(CameraSelector.LENS_FACING_FRONT)
//                    .requireLensFacing(CameraSelector.LENS_FACING_BACK) // todo
                    .build()
                val cameraExecutor: ExecutorService = Executors.newSingleThreadExecutor()
                val cameraProviderFuture: ListenableFuture<ProcessCameraProvider> =
                    ProcessCameraProvider.getInstance(context)

                cameraProviderFuture.addListener({
                    preview = Preview.Builder().build().also {
                        it.setSurfaceProvider(previewView.surfaceProvider)
                    }
                    cameraProvider = cameraProviderFuture.get()
                    val barcodeAnalyser = BarCodeAnalyser { barcodes ->
                        barcodes.forEach { barcode ->
                            barcode.rawValue.let { barcodeValue ->
                                if (barcodeValue != null) {
                                    barCodeVal.value = barcodeValue
                                }
                                /*--------------TODO------------*/
                                qrViewModel.decodingQrCode(barcodeValue.toString())
                            }
                        }
                    }
                    val imageAnalysis: ImageAnalysis = ImageAnalysis.Builder()
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build()
                        .also {
                            it.setAnalyzer(cameraExecutor, barcodeAnalyser)
                        }

                    try {
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            cameraSelector,
                            preview,
                            imageAnalysis
                        )
                    } catch (e: Exception) {
                        Log.d("TAG", "CameraPreview: ${e.localizedMessage}")
                    }
                }, ContextCompat.getMainExecutor(context))
            }
        )
    }

    LaunchedEffect(true) {
        qrViewModel.qrDecodingState.collect {
            if (it.isSuccess) {
                cameraProvider.shutdown()
                val sendUserAccount = qrViewModel.sendUserInfo.value!!
                qrViewModel.remit(
                    RemitRequest(sendUserAccount.id.toInt(), productPrice, 185)
                )
//                navController.navigate(
//                    Screen.Load.route
//                        .replace(
//                            oldValue = "{price}",
//                            newValue =  productPrice.toString()
//                        )
//                )
            }
            if (it.error.isNotEmpty()) {
                Toast.makeText(context, "QR코드를 다시 생성해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    LaunchedEffect(true) {
        qrViewModel.remitState.collect {
            if (it.isSuccess) {
                delay(1000)
                navController.navigate(
                    Screen.Success.route
                        .replace(
                            oldValue = "{price}",
                            newValue = productPrice.toString()
                        )
                )
            }
            if (it.error.isNotEmpty()) {
                delay(1000)
                navController.navigate(Screen.Failed.route)
            }
        }
    }
}
