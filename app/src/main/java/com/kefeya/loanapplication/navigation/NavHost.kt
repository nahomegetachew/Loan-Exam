package com.kefeya.loanapplication.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kefeya.loanapplication.ui.components.BottomNavItems
import com.kefeya.loanapplication.ui.components.BottomNavigationBar
import com.kefeya.loanapplication.ui.screens.AppInit.InitScreen
import com.kefeya.loanapplication.ui.screens.History.LoansHistoryScreen
import com.kefeya.loanapplication.ui.screens.HomeScreen.HomeScreen
import com.kefeya.loanapplication.ui.screens.LoanDetail.LoanDetailScreen
import com.kefeya.loanapplication.ui.screens.Loans.LoansScreen
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
        override var current by remember {
            mutableStateOf<Screen>(Screen.InitScreen)
        }
        override var param by remember {
            mutableStateOf<Any?>(null)
        }
        override fun navigate(page: Screen, cleanBackstack: Boolean, noBack: Boolean, navParam: Any?) {
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
            navParam?.let {
                param = it
            }
            current = page
        }
    }
    val bottomNavItemDestination = listOf(
        Screen.Home.name,
        Screen.Loans.name,
        Screen.History.name,
        Screen.LoanDetail.name)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Column() {
//        Text(text = currentRoute.toString())
//        Text(text = navigator.current.name)
//        Text(text = (bottomNavItemDestination.contains(currentRoute.toString())).toString())
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                if (bottomNavItemDestination.contains(currentRoute.toString())) {
                    BottomNavigationBar(navigator)
                }
            }
        ) {padding ->
            NavHost(
                modifier = Modifier.padding(padding),
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
                composable(Screen.Home.name) { // Define HomeScreen destination
                    HomeScreen(
                        navigator
                    )
                }
                composable(Screen.Loans.name) { // Define HomeScreen destination
                    LoansScreen(
                        navigator
                    )
                }
                composable(Screen.LoanDetail.name) { // Define HomeScreen destination
                    LoanDetailScreen(
                        navigator
                    )
                }
                composable(Screen.History.name) { // Define HomeScreen destination
                    LoansHistoryScreen(
                        navigator
                    )
                }
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
    open var current by  mutableStateOf<Screen>(Screen.InitScreen)
    open var param by  mutableStateOf<Any?>(null)
    abstract fun navigate(page: Screen, cleanBackstack:Boolean = false, noBack:Boolean = false,navParam:Any?=null)
}