package com.kefeya.loanapplication.data.models

data class Loan(
    val amount:Int,
    val term:Int,
    val amountToRepay:Int,
    val rePayedAmount:Int
)
