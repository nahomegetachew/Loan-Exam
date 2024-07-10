package com.kefeya.loanapplication.ui.screens.Loans

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kefeya.loanapplication.data.dao.LoanWithRepayments
import com.kefeya.loanapplication.navigation.Navigator
import com.kefeya.loanapplication.navigation.Screen
import com.kefeya.loanapplication.ui.components.LoanItem

@Composable
fun LoansScreen(navigator: Navigator) {
    val viewModel = viewModel<LoansViewModel>()
    val loans by remember {
        viewModel.loanWithRepayments
    }
    LaunchedEffect(Unit){
        viewModel.getAllLoansWithRepayments()
    }
    Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)){
            loans.forEach {loan ->
                item {
                    LoanItem(loan){
                        navigator.navigate(Screen.LoanDetail,false,false,loan.loan.id)
                    }
                }
            }
        }
    }
}