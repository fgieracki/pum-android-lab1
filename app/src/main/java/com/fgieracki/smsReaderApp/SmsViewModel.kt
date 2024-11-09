package com.fgieracki.smsReaderApp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SmsViewModel : ViewModel() {
    private val _smsList = MutableStateFlow<List<String>>(emptyList())
    val smsList = _smsList.asStateFlow()

    fun addSms(message: String) {
        _smsList.value += message
    }
}
