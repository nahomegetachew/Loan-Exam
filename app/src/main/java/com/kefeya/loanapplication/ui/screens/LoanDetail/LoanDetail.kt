package com.kefeya.loanapplication.ui.screens.LoanDetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kefeya.loanapplication.data.models.Repayment
import com.kefeya.loanapplication.navigation.Navigator
import com.kefeya.loanapplication.ui.components.LoanItem

@Composable
fun LoanDetailScreen(navigator: Navigator?){
    val loanId = navigator?.param.toString().toIntOrNull()
    val viewModel = viewModel<LoanDetailViewModel>()
    var repayAmount by remember {
        viewModel.repaymentAmount
    }
    var repayAmountError by remember {
        viewModel.repaymentAmountError
    }
    val loan by remember {
        viewModel.loanWithRepayments
    }
    LaunchedEffect(Unit){
        loanId?.let {
            viewModel.getLoan(it)
        }
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)) {
        loan?.let {
            LoanItem(loanWithRepayments = it)
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = repayAmount,
                onValueChange ={
                    repayAmount = it
                }
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Button(onClick = {
                loanId?.let {
                    viewModel.insertRepayment(loanId)
                }
            }) {
                Text(text = "Repay")
            }
        }
        Text(text = "Repayments")
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),verticalArrangement = Arrangement.spacedBy(10.dp)) {
            loan?.repayments?.forEach {
                item {
                    RepaymentItem(repayment = it)
                }
            }
        }
    }
}


@Composable
fun RepaymentItem(repayment: Repayment, onClick: () -> Unit = {}){
    Card (modifier = Modifier
        .fillMaxWidth()
        .clickable(onClick = {
            onClick()
        })){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(text = "Repayment amount ${repayment.amount}", style = MaterialTheme.typography.titleMedium)
        }
    }
}