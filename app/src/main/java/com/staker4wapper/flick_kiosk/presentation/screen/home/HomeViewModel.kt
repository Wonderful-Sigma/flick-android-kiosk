package com.staker4wapper.flick_kiosk.presentation.screen.home

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.staker4wapper.flick_kiosk.data.room.Product
import com.staker4wapper.flick_kiosk.data.room.ProductDao
import dagger.hilt.android.lifecycle.HiltViewModel
import io.viascom.nanoid.NanoId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import newjeans.bunnies.data.DataManager
import newjeans.bunnies.main.state.CreateProductState
import okhttp3.Dispatcher
import java.io.ByteArrayOutputStream
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val provideAppDao: ProductDao,
) : ViewModel() {

    init {
        getAllProducts()
    }

    private var _createProductState = MutableSharedFlow<CreateProductState>()
    val createProductState: SharedFlow<CreateProductState> = _createProductState

    var productList: LiveData<List<Product>> = MutableLiveData<List<Product>>(listOf())

    private val _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> = _state

    private val _coin = MutableLiveData<Int>()
    val coin: LiveData<Int> = _coin


    fun getCoin(dataManager: DataManager) {
        viewModelScope.launch {
            val coin = dataManager.getCoin().first()
            if(coin.isNotEmpty()){
                _coin.value = coin.toInt()
            } else {
                _coin.value = 0
            }
        }
    }

    fun getAllProducts() = viewModelScope.launch {
        kotlin.runCatching {
            provideAppDao.products()
        }.onSuccess {
            Log.d(TAG, "getAllProducts: SUCCESS!! $it")
            productList = it.asLiveData()
        }.onFailure { e ->
            Log.d(TAG, "getAllProducts: FAILED.. $e")
        }
    }

    fun changeState() {
        _state.value = !(_state.value ?: false)
    }

    fun createProduct(name: String, price: Int, uri: Uri?, contentResolver: ContentResolver) =
        CoroutineScope(Dispatchers.IO).launch {
            kotlin.runCatching {
                val bitmapImage = setImageBitmap(uri, contentResolver)
                val product = Product(
                    NanoId.generate(10), name, price, bitmapToByteArray(bitmapImage)
                )
                provideAppDao.insert(product)
            }.onSuccess {
                getAllProducts()
                Log.d(TAG, "만들어짐")
                _createProductState.emit(CreateProductState(true))
            }.onFailure {
                _createProductState.emit(CreateProductState())
            }
        }

    fun bitmapToByteArray(bitmap: Bitmap?): ByteArray? {
        if (bitmap == null) {
            Log.d(TAG, "null")
            return null
        }
        var outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, outputStream)
        return outputStream.toByteArray()
    }

    fun deleteProduct(uid: String) = viewModelScope.launch {
        kotlin.runCatching {
            provideAppDao.delete(uid)
        }.onSuccess {
            getAllProducts()
        }
    }

    fun setImageBitmap(uri: Uri?, contentResolver: ContentResolver): Bitmap? {
        if (uri == null) return null
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) ImageDecoder.decodeBitmap(
            ImageDecoder.createSource(contentResolver, uri)
        ) else MediaStore.Images.Media.getBitmap(contentResolver, uri)
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }

}