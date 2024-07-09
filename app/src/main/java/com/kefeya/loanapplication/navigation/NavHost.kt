package com.kefeya.loanapplication.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kefeya.loanapplication.ui.screens.AppInit.InitScreen
import com.kefeya.loanapplication.ui.screens.HomeScreen.HomeScreen
import com.kefeya.loanapplication.ui.screens.login.LoginScreen
import com.kefeya.loanapplication.ui.screens.register.RegisterScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController() // Create a NavController to manage navigation
     fun navigateToPage(page: Screen, cleanBackstack:Boolean = false) {
        if (cleanBackstack){
            navController.navigateAndClean(page.name)
        }else{
            navController.navigate(page.name)
        }
    }
    val navigator = object : Navigator() {
        override fun navigate(page: Screen, cleanBackstack:Boolean, noBack:Boolean) {
            if (cleanBackstack){
                navController.navigateAndClean(page.name)
            }else{
                if (noBack){
                    navController.navigate(page.name){
                        popUpTo(current.name){
                            inclusive = true
                        }
                    }
                }else{
                    navController.navigate(page.name)
                }
            }
            current = page
        }
    }
    Column {
        NavHost(
            navController = navController,
            startDestination = Screen.InitScreen.name
        ) {
            composable(Screen.InitScreen.name) { // Define HomeScreen destination
                InitScreen(
                    navigator
                )
            }

            composable(Screen.Register.name) { // Define HomeScreen destination
                RegisterScreen(
                    navigator
                )
            }
            composable(Screen.Login.name) { // Define HomeScreen destination
                LoginScreen(
                    navigator
                )
            }
            composable(Screen.Loan.name) { // Define HomeScreen destination
                HomeScreen(
                    navigator
                )
            }
        }
    }
}

fun NavHostController.navigateAndClean(route: String) {
    navigate(route) {
        popUpTo(0)
    }
}

fun NavHostController.navigateToPage(route: Screen) {
    navigate(route.name) {
        popUpTo(0)
    }
}


abstract class Navigator(
) {
    var current: Screen = Screen.InitScreen
    abstract fun navigate(page: Screen, cleanBackstack:Boolean = false, noBack:Boolean = false)
}