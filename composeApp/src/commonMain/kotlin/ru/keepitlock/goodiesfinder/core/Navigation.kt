package ru.keepitlock.goodiesfinder.core

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.keepitlock.goodiesfinder.presentation.add.AddProductScreen
import ru.keepitlock.goodiesfinder.presentation.components.BottomNavigationBar
import ru.keepitlock.goodiesfinder.presentation.list.ProductListScreen

sealed class Screen(val route: String) {

    object Add : Screen("add")

    object List : Screen("list")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Screen.Add.route

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = currentRoute,
                onNavigate = { route ->
                    if (currentRoute != route) {
                        navController.navigate(route)
                    }
                }
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Add.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Add.route) {
                AddProductScreen(
                    onNavigateToHome = {
                        navController.navigate(Screen.Add.route) {
                            popUpTo(Screen.Add.route) { inclusive = false }
                        }
                    }
                )
            }
            composable(Screen.List.route) {
                ProductListScreen()
            }
        }
    }
}