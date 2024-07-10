package com.kefeya.loanapplication.ui.screens.AppInit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.kefeya.loanapplication.navigation.Navigator
import com.kefeya.loanapplication.navigation.Screen

@Composable()
fun InitScreen(navigator: Navigator? = null){
    val context = LocalContext.current
    val viewModel = InitViewModel()
    val registrationStatus by remember{ viewModel.registrationStatus }
    LaunchedEffect(Unit){
        viewModel.loadRegistrationStatus(context)
    }

    LaunchedEffect(registrationStatus){
        if (registrationStatus != "NONE"){
            if (registrationStatus == "REGISTERED"){
                navigator?.navigate(Screen.Login,true)
            }
            else{
                navigator?.navigate(Screen.Register,true,true)
            }
        }
    }
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Loading...")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun preview(){
    InitScreen()
}