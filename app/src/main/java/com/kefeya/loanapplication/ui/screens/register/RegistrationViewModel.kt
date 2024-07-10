package com.kefeya.loanapplication.ui.screens.register

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchat.tipthunder.utils.dataStore.DataKeys
import com.kirchat.tipthunder.utils.dataStore.writeString
import kotlinx.coroutines.launch

class RegistrationViewModel : ViewModel() {
    val registered = mutableStateOf(false)
    var username = mutableStateOf("")
    var usernameError = mutableStateOf("")
    var email = mutableStateOf("")
    var emailError = mutableStateOf("")
    var password = mutableStateOf("")
    var passwordError = mutableStateOf("")
    var confirmPassword = mutableStateOf("")
    var confirmPasswordError = mutableStateOf("")

    fun register(context: Context){
        var hasError = false

        usernameError.value = ""
        emailError.value = ""
        passwordError.value = ""
        confirmPasswordError.value = ""



        usernameError.value = validateNotEmpty("username",username.value)
        emailError.value = validateNotEmpty("password",email.value)
        passwordError.value = validateNotEmpty("password",password.value)
        confirmPasswordError.value = validateNotEmpty("confirm password",confirmPassword.value)
        if (confirmPassword.value != password.value){
            passwordError.value = "passwords must match"
            confirmPasswordError.value = "passwords must match"
        }

        hasError = listOf(
            usernameError.value.isNotEmpty(),
            emailError.value.isNotEmpty(),
            passwordError.value.isNotEmpty(),
            confirmPasswordError.value.isNotEmpty()
        ).any { it }

        if (!hasError){
            viewModelScope.launch {
                context.writeString(DataKeys.REGISTRATION_STATUS,"REGISTERED")
                context.writeString(DataKeys.USERNAME,username.value)
                context.writeString(DataKeys.PASSWORD,password.value)
                registered.value = true
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