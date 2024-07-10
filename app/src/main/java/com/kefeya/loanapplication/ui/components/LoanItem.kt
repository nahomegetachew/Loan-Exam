package com.kefeya.loanapplication.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kefeya.loanapplication.data.dao.LoanWithRepayments

@Composable
fun LoanItem(loanWithRepayments: LoanWithRepayments, onClick: () -> Unit = {}){
    Card (modifier = Modifier
        .fillMaxWidth()
        .clickable(onClick = {
            onClick()
        })){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(text = "Amount ${loanWithRepayments.loan.amount}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Remaining Repay Amount ${loanWithRepayments.remainingAmount}",style = MaterialTheme.typography.titleMedium)
            if (loanWithRepayments.remainingAmount == 0){
                Text(text = "Done", color = Color.Blue, style = MaterialTheme.typography.titleMedium)
            }else{
                Text(text = "Pending", color = Color.Red, style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}