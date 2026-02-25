package ru.keepitlock.goodiesfinder.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import ru.keepitlock.goodiesfinder.core.Screen

@Composable
fun BottomNavigationBar(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp
    ) {
        val items = listOf(
            Screen.List to "Список",
            Screen.Add to "Добавить"
        )

        items.forEach { (screen, label) ->
            NavigationBarItem(
                icon = {
                    // Для простоты используем текст или заглушку,
                    // в реальном проекте нужны Icons.Default.*
                    Text(if(screen == Screen.List) "📋" else "➕")
                },
                label = { Text(label) },
                selected = currentRoute == screen.route,
                onClick = { onNavigate(screen.route) }
            )
        }
    }
}