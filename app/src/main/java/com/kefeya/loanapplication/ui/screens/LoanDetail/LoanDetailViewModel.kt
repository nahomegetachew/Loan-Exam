package com.kefeya.loanapplication.ui.screens.LoanDetail

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kefeya.loanapplication.data.dao.LoanDao
import com.kefeya.loanapplication.data.dao.LoanWithRepayments
import com.kefeya.loanapplication.data.dao.RepaymentDao
import com.kefeya.loanapplication.data.models.Repayment
import com.kefeya.loanapplication.utils.database.LoanDatabase
import kotlinx.coroutines.launch

class LoanDetailViewModel (application: Application) : AndroidViewModel(application) {
    private val loanDao: LoanDao = LoanDatabase.getDatabase(application).loanDao()
    private val repaymentDao: RepaymentDao = LoanDatabase.getDatabase(application).repaymentDao()
    var repaymentAmount = mutableStateOf("")
    var repaymentAmountError = mutableStateOf("")
    private val _loanWithRepayments = mutableStateOf<LoanWithRepayments?>(null)
    val loanWithRepayments: MutableState<LoanWithRepayments?> = _loanWithRepayments


    fun getLoan(loanId:Int){
        viewModelScope.launch {
            loanDao.getLoanWithRepayments(loanId).let {
                _loanWithRepayments.value = it
            }
        }
    }

    fun insertRepayment(loanId: Int) {
        val amount = repaymentAmount.value.toIntOrNull() ?: 0
        viewModelScope.launch {
            val loanWithRemainingAmount = loanDao.getLoanWithRemainingAmount(loanId)
            if (amount > loanWithRemainingAmount.remainingAmount) {
                repaymentAmount.value = "Repayment amount cannot exceed the remaining loan amount of ${loanWithRemainingAmount.remainingAmount}"
            } else {
                val repayment = Repayment(loanId = loanId, amount = amount)
                repaymentDao.insertRepayment(repayment)
                getLoanWithRepayments(loanId)
            }
        }
    }

    fun getLoanWithRepayments(loanId: Int) {
        viewModelScope.launch {
            _loanWithRepayments.value = loanDao.getLoanWithRepayments(loanId)
        }
    }

}