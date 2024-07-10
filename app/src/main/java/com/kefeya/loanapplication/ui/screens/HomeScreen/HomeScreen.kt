package com.kefeya.loanapplication.ui.screens.HomeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kefeya.loanapplication.navigation.Navigator
import com.kefeya.loanapplication.navigation.Screen

@Composable
fun HomeScreen(navigator: Navigator?) {
    val loanViewModel = viewModel<HomeViewModel>()
    var loanAmount by remember { loanViewModel.amount }
    var loanTerm by remember { loanViewModel.term }
    val loanError by remember { loanViewModel.loanError }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = loanAmount,
            onValueChange = { loanAmount = it },
            label = { Text("Loan Amount") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = loanTerm,
            onValueChange = { loanTerm = it },
            label = { Text("Loan Term (months)") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (loanError.isNotBlank()) {
            Text(text = loanError, color = Color.Red)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                loanViewModel.addLoan(){
                    navigator?.navigate(Screen.Loans)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit Application")
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun  Preview(){
    HomeScreen(navigator = null)
}
