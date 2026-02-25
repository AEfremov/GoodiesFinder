package ru.keepitlock.goodiesfinder

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform