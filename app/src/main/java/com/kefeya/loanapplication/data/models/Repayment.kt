package com.kefeya.loanapplication.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repayments")
data class Repayment(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val loanId: Int,
    val amount: Int,
    val date: Long = System.currentTimeMillis()
)
