package com.kefeya.loanapplication.ui.screens.AppInit

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchat.tipthunder.utils.dataStore.DataKeys
import com.kirchat.tipthunder.utils.dataStore.readString
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class InitViewModel : ViewModel() {
    var registrationStatus = mutableStateOf("NONE")

    fun loadRegistrationStatus(context: Context){
        viewModelScope.launch {
            registrationStatus.value = context.readString(DataKeys.REGISTRATION_STATUS).first()
        }
    }
}