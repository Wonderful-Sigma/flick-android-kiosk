package com.staker4wapper.flick_kiosk.presentation.Screen.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.staker4wapper.flick_kiosk.data.dto.ProductResponse
import com.staker4wapper.flick_kiosk.data.repository.ProductRepository
import com.staker4wapper.flick_kiosk.presentation.Screen.qrcode.QRViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository
): ViewModel() {

    private val _productList = MutableLiveData<List<ProductResponse>>()
    val productList: LiveData<List<ProductResponse>> = _productList

    fun getAllProducts(walletId: Int) = viewModelScope.launch {
        kotlin.runCatching {
            productRepository.getAllProduct(walletId)
        }.onSuccess {
            Log.d(TAG, "getAllProducts: SUCCESS!! $it")
            _productList.value = it
        }.onFailure { e ->
            Log.d(TAG, "getAllProducts: FAILED.. $e")
        }
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }

}