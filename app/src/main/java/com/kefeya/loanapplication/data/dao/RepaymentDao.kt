package com.kefeya.loanapplication.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kefeya.loanapplication.data.models.Repayment

@Dao
interface RepaymentDao {
    @Insert
    suspend fun insertRepayment(repayment: Repayment): Long

    @Query("SELECT * FROM repayments WHERE loanId = :loanId")
    suspend fun getRepaymentsForLoan(loanId: Int): List<Repayment>
}
