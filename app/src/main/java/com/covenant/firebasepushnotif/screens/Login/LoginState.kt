package com.covenant.firebasepushnotif.screens.Login

data class LoginState (
    val email: String = "",
    val password: String = "",
    val loginState: Boolean = false,
)
class LoginStateChange(
    val onEmailChange: (String) -> Unit,
    val onPasswordChange: (String) -> Unit,
    val onLogin: () -> Unit,
)