package com.covenant.firebasepushnotif.screens.Register

data class RegisterState (
    val email: String = "",
    val password: String = "",
    val repeatedPassword: String = "",
    val registered: Boolean = false,
)
class RegisterStateChange(
    val onEmailChange: (String) -> Unit,
    val onPasswordChange: (String) -> Unit,
    val onRepeatedPasswordChange: (String) -> Unit,
    val onRegister: () -> Unit,
)
