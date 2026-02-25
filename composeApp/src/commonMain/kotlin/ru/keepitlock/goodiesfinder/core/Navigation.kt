package ru.keepitlock.goodiesfinder.core

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.keepitlock.goodiesfinder.presentation.add.AddProductScreen
import ru.keepitlock.goodiesfinder.presentation.components.BottomNavigationBar
import ru.keepitlock.goodiesfinder.presentation.list.ProductListScreen

sealed class Screen(val route: String) {
    object List : Screen("list")
    object Add : Screen("add")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Screen.List.route

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
            startDestination = Screen.List.route,
            modifier = androidx.compose.ui.Modifier.padding(paddingValues)
        ) {
            composable(Screen.List.route) {
                ProductListScreen()
            }
            composable(Screen.Add.route) {
                AddProductScreen(
                    onNavigateToHome = {
                        navController.navigate(Screen.List.route) {
                            popUpTo(Screen.List.route)
                        }
                    }
                )
            }
        }
    }
}