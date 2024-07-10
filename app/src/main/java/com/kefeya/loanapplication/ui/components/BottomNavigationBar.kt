package com.kefeya.loanapplication.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.kefeya.loanapplication.navigation.Navigator

@Composable
fun BottomNavigationBar(navigator: Navigator?) {
    NavigationBar {
        BottomNavItems.forEach {
            NavigationBarItem(
                selected = navigator?.current == it.route,
                onClick = {
                    navigator?.navigate(it.route)
                },
                icon = { Icon(it.icon, contentDescription = null) },
                label = {Text(it.label)})
        }
    }
}