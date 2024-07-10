package com.kefeya.loanapplication.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.kefeya.loanapplication.data.models.Loan
import com.kefeya.loanapplication.data.models.Repayment

@Dao
interface LoanDao {
    @Insert
    suspend fun insertLoan(loan: Loan): Long

    @Insert
    suspend fun insertRepayment(repayment: Repayment): Long

    @Query("SELECT * FROM loans")
    suspend fun getAllLoans(): List<Loan>

    @Query("SELECT * FROM loans WHERE id = :loanId LIMIT 1")
    suspend fun getLoanById(loanId: Int): Loan

    @Transaction
    @Query("SELECT * FROM loans WHERE id = :loanId")
    suspend fun getLoanWithRepayments(loanId: Int): LoanWithRepayments

    @Transaction
    @Query("SELECT * FROM loans")
    suspend fun getAllLoansWithRepayments(): List<LoanWithRepayments>

    @Query("""
        SELECT l.*, (l.amount - IFNULL(SUM(r.amount), 0)) AS remainingAmount 
        FROM loans l 
        LEFT JOIN repayments r ON l.id = r.loanId 
        GROUP BY l.id
        HAVING l.id = :loanId
    """)
    suspend fun getLoanWithRemainingAmount(loanId: Int): Loan

    @Query("""
        SELECT l.*, (l.amount - IFNULL(SUM(r.amount), 0)) AS remainingAmount 
        FROM loans l 
        LEFT JOIN repayments r ON l.id = r.loanId 
        GROUP BY l.id
    """)
    suspend fun getAllLoansWithRemainingAmounts(): List<Loan>
}
