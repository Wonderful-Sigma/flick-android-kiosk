package com.staker4wapper.flick_kiosk.presentation.Screen.qrcode

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.staker4wapper.flick_kiosk.data.dto.QrDecodingResponse
import com.staker4wapper.flick_kiosk.data.dto.RemitRequest
import com.staker4wapper.flick_kiosk.data.repository.QRCodeRepository
import com.staker4wapper.flick_kiosk.presentation.Screen.qrcode.state.QRDecodingState
import com.staker4wapper.flick_kiosk.presentation.Screen.qrcode.state.RemitState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QRViewModel @Inject constructor(
    private val qrCodeRepository: QRCodeRepository
): ViewModel() {

    private val _qrDecodingState = MutableSharedFlow<QRDecodingState>()
    val qrDecodingState: SharedFlow<QRDecodingState> = _qrDecodingState

    private val _remitState = MutableSharedFlow<RemitState>()
    val remitState: SharedFlow<RemitState> = _remitState

    private val _sendUserInfo = MutableLiveData<QrDecodingResponse>()
    val sendUserInfo: LiveData<QrDecodingResponse> = _sendUserInfo

    fun decodingQrCode(jwt: String) = viewModelScope.launch {
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

    fun remit(remitRequest: RemitRequest) = viewModelScope.launch {
        kotlin.runCatching {
            qrCodeRepository.remit(remitRequest)
        }.onSuccess {
            Log.d(TAG, "remit: SUCCESS!! $it")
            _remitState.emit(RemitState(isSuccess = true))
        }.onFailure { e ->
            Log.d(TAG, "remit: FAILED.. $e")
            _remitState.emit(RemitState(error = "$e"))
        }
    }


    companion object {
        private const val TAG = "QRViewModel"
    }
}