package com.kefeya.loanapplication.data.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters


@Entity(tableName = "loans")
@TypeConverters(LoanStatusConverter::class)
data class Loan(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val amount: Int,
    val term: Int,
    val status: LoanStatus = LoanStatus.PENDING
){
    @Ignore
    var remainingAmount: Int = amount
}

enum class LoanStatus {
    PENDING,
    PAID
}

class LoanStatusConverter {
    @TypeConverter
    fun fromLoanStatus(status: LoanStatus): String {
        return status.name
    }

    @TypeConverter
    fun toLoanStatus(status: String): LoanStatus {
        return LoanStatus.valueOf(status)
    }
}