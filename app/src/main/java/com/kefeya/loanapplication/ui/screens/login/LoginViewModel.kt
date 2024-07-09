package com.kefeya.loanapplication.ui.screens.login

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchat.tipthunder.utils.dataStore.DataKeys
import com.kirchat.tipthunder.utils.dataStore.readString
import com.kirchat.tipthunder.utils.dataStore.writeString
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class LoginViewModel :ViewModel() {
    val logedIn = mutableStateOf(false)
    val username = mutableStateOf("")
    val usernameError = mutableStateOf("")
    val password = mutableStateOf("")
    val passwordError = mutableStateOf("")
    val loginError = mutableStateOf("")

    fun login(context: Context){
        var hasError = false
        usernameError.value = ""
        passwordError.value = ""

        usernameError.value = validateNotEmpty(
            "username",
            username.value
        )
        passwordError.value = validateNotEmpty(
            "password",
            password.value
        )

        hasError = listOf(
            usernameError.value.isNotEmpty(),
            passwordError.value.isNotEmpty(),
        ).any { it }

        if (!hasError){
            viewModelScope.launch {
                val currentUsername = context.readString(DataKeys.USERNAME).first()
                val currentPassword = context.readString(DataKeys.PASSWORD).first()
                if (currentUsername != username.value || currentPassword != password.value){
                    loginError.value = "wrong username or password"
                }else{
                    logedIn.value = true
                }
        }
    }
    }
}

fun validateNotEmpty(name:String,value:String): String {
    if (value.isBlank()){
        return "$name should not be empty";
    }
    return  ""
}