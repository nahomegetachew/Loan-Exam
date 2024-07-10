package com.kefeya.loanapplication.utils.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kefeya.loanapplication.data.dao.LoanDao
import com.kefeya.loanapplication.data.dao.RepaymentDao
import com.kefeya.loanapplication.data.models.Loan
import com.kefeya.loanapplication.data.models.Repayment

@Database(entities = [Loan::class, Repayment::class], version = 2, exportSchema = false)
abstract class LoanDatabase : RoomDatabase() {
    abstract fun loanDao(): LoanDao
    abstract fun repaymentDao(): RepaymentDao


    companion object {
        @Volatile
        private var INSTANCE: LoanDatabase? = null
        fun getDatabase(context: Context): LoanDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LoanDatabase::class.java,
                    "loan_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}