package com.kefeya.loanapplication.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kefeya.loanapplication.navigation.Navigator
import com.kefeya.loanapplication.navigation.Screen

@Composable()
fun LoginScreen(navigator: Navigator? = null) {
    val context = LocalContext.current
    val viewModel = viewModel<LoginViewModel>()
    var logedIn by remember {
        viewModel.logedIn
    }
    var username by remember {
        viewModel.username
    }
    var usernameError by remember {
        viewModel.usernameError
    }
    var password by remember {
        viewModel.password
    }
    var passwordError by remember {
        viewModel.passwordError
    }
    var loginError by remember {
        viewModel.loginError
    }
    LaunchedEffect(logedIn){
        if (logedIn){
            navigator?.navigate(Screen.Home,true,true)
        }
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

//        Text(text = username)
//        Text(text = usernameError)
//        Text(text = password)
//        Text(text = passwordError)
        Column(verticalArrangement = Arrangement.spacedBy(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(
                isError = usernameError.isNotBlank(),
                modifier = Modifier.fillMaxWidth(),
                value = username,
                onValueChange = {
                    username = it
                },
                placeholder = { Text(text = "Username") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = {
                    password = it
                },
                placeholder = { Text(text = "Password") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            if (usernameError.isNotBlank()){
                Text(text = usernameError, color = Color.Red)
            }
            if (passwordError.isNotBlank()){
                Text(text = passwordError, color = Color.Red)
            }
            if(loginError.isNotBlank()){
                Text(text = loginError, color = Color.Red)
            }
            Button(modifier = Modifier.fillMaxWidth(),onClick = {
                viewModel.login(context)
            }) {
                Text(text = "Login")
            }
        }

    }
}