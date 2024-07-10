package com.kefeya.loanapplication.ui.screens.HomeScreen

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kefeya.loanapplication.data.dao.LoanDao
import com.kefeya.loanapplication.data.dao.LoanWithRepayments
import com.kefeya.loanapplication.data.models.Loan
import com.kefeya.loanapplication.utils.database.LoanDatabase
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val loanError = mutableStateOf("")
    val amount = mutableStateOf("")
    val term = mutableStateOf("")
    private val loanDao: LoanDao = LoanDatabase.getDatabase(application).loanDao()


    private val _loanWithRepayments = MutableLiveData<List<LoanWithRepayments>>()


    fun addLoan(onSuccess: () -> Unit ={}){
        val loanAmount = amount.value.toIntOrNull() ?: 0
        val loanTerm = term.value.toIntOrNull() ?: 0
        loanError.value = ""
        if (loanAmount > 1000 || loanAmount < 10){
            loanError.value = "invalid loan amount"
        }
        if (loanTerm > 30 || loanTerm < 1){
            loanError.value = "invalid loan term"
        }
        if (loanError.value.isNotBlank()) return;
        viewModelScope.launch {
            val loan = Loan(amount = loanAmount, term = loanTerm)
            loanDao.insertLoan(loan)
            amount.value = ""
            term.value = ""
            onSuccess()
        }

    }

}
