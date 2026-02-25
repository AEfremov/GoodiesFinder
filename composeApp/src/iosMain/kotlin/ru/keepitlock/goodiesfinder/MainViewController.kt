package ru.keepitlock.goodiesfinder

import androidx.compose.ui.window.ComposeUIViewController
import org.koin.core.context.startKoin
import org.koin.mp.KoinPlatform.startKoin

fun MainViewController() = ComposeUIViewController {
    startKoin {
        modules(dataModule, viewModelModule)
    }
    App()
}