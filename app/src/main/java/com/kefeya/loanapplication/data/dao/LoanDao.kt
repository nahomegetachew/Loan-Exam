package com.kefeya.loanapplication.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.kefeya.loanapplication.data.models.Loan
import com.kefeya.loanapplication.data.models.LoanStatus
import com.kefeya.loanapplication.data.models.Repayment

@Dao
interface LoanDao {
        @Insert
        suspend fun insertLoan(loan: Loan): Long

        @Insert
        suspend fun insertRepayment(repayment: Repayment): Long

        @Query("SELECT * FROM loans WHERE status = :status")
        suspend fun getLoansByStatus(status: LoanStatus): List<Loan>

        @Transaction
        @Query("""
        SELECT * FROM loans WHERE id = :loanId
    """)
        suspend fun getLoanWithRepayments(loanId: Int): LoanWithRepayments

        @Transaction
        @Query("""
        SELECT * FROM loans
        WHERE id IN (
            SELECT loans.id
            FROM loans
            LEFT JOIN repayments ON loans.id = repayments.loanId
            GROUP BY loans.id
            HAVING SUM(COALESCE(repayments.amount, 0)) < loans.amount
        )
        ORDER BY id DESC
    """)
        suspend fun getAllUnpaidLoansWithRepayments(): List<LoanWithRepayments>

        @Query("UPDATE loans SET status = :status WHERE id = :loanId")
        suspend fun updateLoanStatus(loanId: Int, status: LoanStatus)

        @Transaction
        @Query("""
        SELECT * FROM loans 
        WHERE id IN (
            SELECT loanId FROM repayments 
            GROUP BY loanId 
            HAVING SUM(amount) = (SELECT amount FROM loans WHERE id = loanId)
        )
        ORDER BY id DESC
    """)
        suspend fun getAllPaidLoansWithRepayments(): List<LoanWithRepayments>



}
