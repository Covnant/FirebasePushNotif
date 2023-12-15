package com.covenant.firebasepushnotif.screens.Login

data class LoginState (
    val email: String = "",
    val hasEmailError: Boolean = false,
    val password: String = "",
    val hasPasswordError: Boolean = false,
){
    val hasError = hasEmailError || hasPasswordError
}
class LoginStateChange(
    val onEmailChange: (String) -> Unit,
    val onPasswordChange: (String) -> Unit,
    val onLogin: () -> Unit,
)