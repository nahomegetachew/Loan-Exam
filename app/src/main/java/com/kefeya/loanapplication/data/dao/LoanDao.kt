package com.kefeya.loanapplication.data.dao

data class LoanDao(
    val amount:Int,
    val term:Int,
    val amountToRepay:Int,
    val rePayedAmount:Int
)
