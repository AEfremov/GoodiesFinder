package ru.keepitlock.goodiesfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.keepitlock.goodiesfinder.core.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    androidx.compose.material3.MaterialTheme(content = content)
}

@Preview
@Composable
fun AppAndroidPreview() {
    AppTheme {
        AppNavigation()
    }
}