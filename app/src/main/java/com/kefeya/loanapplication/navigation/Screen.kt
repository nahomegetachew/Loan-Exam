package com.kefeya.loanapplication.navigation

sealed class Screen(var name:String) {
    object InitScreen : Screen(name = "Init")
    object Login : Screen(name = "Login")
    object Register : Screen(name = "register")
    object Loan : Screen(name = "Loan")
    object LoanDetail : Screen(name = "LoanDetail")
    object Repayments : Screen(name = "Repayments")
}