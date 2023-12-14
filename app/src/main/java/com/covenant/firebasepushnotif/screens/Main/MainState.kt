package com.covenant.firebasepushnotif.screens.Main

data class MainState(
    val email: String = "",
)
class MainStateChange(
    val onLogout: () -> Unit,
)
