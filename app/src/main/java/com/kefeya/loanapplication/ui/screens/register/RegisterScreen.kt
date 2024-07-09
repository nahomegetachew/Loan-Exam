package com.kefeya.loanapplication.ui.screens.register

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.traceEventStart
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kefeya.loanapplication.navigation.Navigator
import com.kefeya.loanapplication.navigation.Screen
import com.kefeya.loanapplication.ui.screens.login.LoginViewModel

@Composable
fun RegisterScreen(navigator: Navigator? = null) {
    val context = LocalContext.current
    val viewModel = viewModel<RegistrationViewModel>()

    val registed by remember {
        viewModel.registered
    }
    var username by remember {
        viewModel.username
    }
    var usernameError by remember {
        viewModel.usernameError
    }
    var email by remember {
        viewModel.email
    }
    var emailError by remember {
        viewModel.emailError
    }
    var password by remember {
        viewModel.password
    }
    var passwordError by remember {
        viewModel.passwordError
    }
    var confirmPassword by remember {
        viewModel.confirmPassword
    }
    var confirmPasswordError by remember {
        viewModel.confirmPasswordError
    }
    LaunchedEffect(registed){
        if (registed){
            navigator?.navigate(Screen.Loan, true,true)
        }
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Column() {
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
                if (usernameError.isNotBlank()) {
                    Text(text = usernameError, color = Color.Red)
                }
            }
            Column() {

                OutlinedTextField(
                    isError = emailError.isNotBlank(),
                    label = { Text("email") },
                    modifier = Modifier.fillMaxWidth(),
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    placeholder = { Text(text = "Email") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
                if (emailError.isNotBlank()) {
                    Text(text = emailError, color = Color.Red)
                }
            }
            Column {

                OutlinedTextField(
                    isError = passwordError.isNotBlank(),
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    placeholder = { Text(text = "Password") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
                if (passwordError.isNotBlank()) {
                    Text(text = passwordError, color = Color.Red)
                }
            }
            Column {
                OutlinedTextField(
                    isError = confirmPasswordError.isNotBlank(),
                    modifier = Modifier.fillMaxWidth(),
                    value = confirmPassword,
                    onValueChange = {
                        confirmPassword = it
                    },
                    placeholder = { Text(text = "Confirm Password") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
            if (confirmPasswordError.isNotBlank()) {
                Text(text = confirmPasswordError, color = Color.Red)
            }
        }

            Button(modifier = Modifier.fillMaxWidth(),onClick = {
                viewModel.register(context)
            }) {
                Text(text = "Register")
            }
        }

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun preview(){
    RegisterScreen(null)
}