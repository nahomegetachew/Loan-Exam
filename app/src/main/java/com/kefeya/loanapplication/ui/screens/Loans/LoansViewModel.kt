package com.kefeya.loanapplication.ui.screens.Loans

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kefeya.loanapplication.data.dao.LoanDao
import com.kefeya.loanapplication.data.dao.LoanWithRepayments
import com.kefeya.loanapplication.utils.database.LoanDatabase
import kotlinx.coroutines.launch

class LoansViewModel(application: Application) : AndroidViewModel(application) {
    private val loanDao: LoanDao = LoanDatabase.getDatabase(application).loanDao()

    private val _loanWithRepayments = mutableStateOf<List<LoanWithRepayments>>(listOf())
    val loanWithRepayments: MutableState<List<LoanWithRepayments>> = _loanWithRepayments

    fun getAllLoansWithRepayments() {
        viewModelScope.launch {
            _loanWithRepayments.value = loanDao.getAllLoansWithRepayments()
        }
    }
}