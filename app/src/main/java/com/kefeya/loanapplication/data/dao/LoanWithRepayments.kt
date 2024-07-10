package com.kefeya.loanapplication.data.dao

import androidx.room.Embedded
import androidx.room.Relation
import com.kefeya.loanapplication.data.models.Loan
import com.kefeya.loanapplication.data.models.Repayment

data class LoanWithRepayments(
    @Embedded val loan: Loan,
    @Relation(
        parentColumn = "id",
        entityColumn = "loanId"
    )
    val repayments: List<Repayment>
){
    val remainingAmount: Int
        get() = loan.amount - repayments.sumOf { it.amount }
}
