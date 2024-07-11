package com.staker4wapper.flick_kiosk.presentation.utils

import android.graphics.Insets.add
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.decode.VideoFrameDecoder
import coil.size.Size


@Composable
fun SingleMediaPicker() {
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
    Column{
        Row {
            //이미지만
            Button(
                onClick = {
                    pickSingleMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                }
            ) {
                Text(text = "Image")
            }
        }

        selectedUri?.let { uri ->
            val context = LocalContext.current
            val imageLoader = ImageLoader.Builder(context)
                .components {
                    add(VideoFrameDecoder.Factory())
                    if (Build.VERSION.SDK_INT >= 28) {
                        add(ImageDecoderDecoder.Factory())
                    } else {
                        add(GifDecoder.Factory())
                    }
                }
                .build()
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(context)
                    .data(uri)
                    .size(Size.ORIGINAL)
                    .build(),
                imageLoader = imageLoader
            )
            Image(
                painter = painter,
                contentDescription = null,
            )
        }
    }
}