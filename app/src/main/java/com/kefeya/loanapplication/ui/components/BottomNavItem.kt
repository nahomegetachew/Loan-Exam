package com.kefeya.loanapplication.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.kefeya.loanapplication.navigation.Screen

data class BottomNavItem(val route: Screen, val icon: ImageVector, val label: String)

val BottomNavItems = listOf(
    BottomNavItem(Screen.Home, Icons.Default.Home, "Home"),
    BottomNavItem(Screen.Loans, Icons.Default.List, "Loans")
)