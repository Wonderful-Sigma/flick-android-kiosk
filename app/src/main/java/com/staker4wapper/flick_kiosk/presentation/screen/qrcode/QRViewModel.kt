package com.staker4wapper.flick_kiosk.presentation.screen.qrcode

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.staker4wapper.flick_kiosk.data.dto.PaymentRequest
import com.staker4wapper.flick_kiosk.data.dto.QrDecodingResponse
import com.staker4wapper.flick_kiosk.data.dto.RemitRequest
import com.staker4wapper.flick_kiosk.data.repository.QRCodeRepository
import com.staker4wapper.flick_kiosk.presentation.screen.qrcode.state.QRDecodingState
import com.staker4wapper.flick_kiosk.presentation.screen.qrcode.state.RemitState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import newjeans.bunnies.data.DataManager
import javax.inject.Inject

@HiltViewModel
class QRViewModel @Inject constructor(
    private val qrCodeRepository: QRCodeRepository,
    private val dataManager: DataManager
) : ViewModel() {
    private val _qrDecodingState = MutableSharedFlow<QRDecodingState>()
    val qrDecodingState: SharedFlow<QRDecodingState> = _qrDecodingState

    private val _remitState = MutableSharedFlow<RemitState>()
    val remitState: SharedFlow<RemitState> = _remitState

    private val _sendUserInfo = MutableLiveData<QrDecodingResponse>()
    val sendUserInfo: LiveData<QrDecodingResponse> = _sendUserInfo

    fun decodingQrCode(jwt: String) = viewModelScope.launch {
        Log.d(TAG, "QRCOde")
        kotlin.runCatching {
            qrCodeRepository.decodingQrCode(jwt)
        }.onSuccess {
            Log.d(TAG, "decoding: SUCCESS!! $it")
            _qrDecodingState.emit(QRDecodingState(isSuccess = true))
            _sendUserInfo.value = it
        }.onFailure { e ->
            Log.d(TAG, "decoding: FAILED.. $e")
            _qrDecodingState.emit(QRDecodingState(error = "$e"))
        }
    }

    fun remit(remitRequest: RemitRequest, dataManager: DataManager) = viewModelScope.launch {
        Log.d(TAG, "돈 빠져나감 ㅠㅠ")
        kotlin.runCatching {
            qrCodeRepository.remit(remitRequest)
        }.onSuccess {
            when (it.status) {
                200 -> {
                    Log.d(TAG, "remit: SUCCESS!! $it")
                    var coin = dataManager.getCoin().first()
                    if (coin.isEmpty()) {
                        coin = "0"
                    }

                    dataManager.saveCoin((coin.toInt() + remitRequest.money.toInt()).toString())
                    _remitState.emit(RemitState(isSuccess = true))
                }

                400 -> {
                    Log.d(TAG, "remit: FAILED.. ${it.message}")
                    _remitState.emit(RemitState(error = it.message))
                }
            }
        }.onFailure { e ->
            Log.d(TAG, "remit: FAILED.. $e")
            _remitState.emit(RemitState(error = "$e"))
        }
    }

    fun payment(paymentRequest: PaymentRequest) = viewModelScope.launch {
        kotlin.runCatching {
            qrCodeRepository.payment(paymentRequest)
        }.onSuccess {
            Log.d(TAG, "payment: FAILED.. $it")
        }.onFailure { e ->
            Log.d(TAG, "payment: FAILED.. $e")
        }
    }

    companion object {
        private const val TAG = "QRViewModel"
    }
}