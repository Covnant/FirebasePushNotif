package com.covenant.firebasepushnotif.screens.Register

data class RegisterState (
    val email: String = "",
    val emailError: Boolean = false,
    val password: String = "",
    val passwordError: Boolean  = false,
    val repeatedPassword: String = "",
    val repeatedPasswordError: Boolean = false,

){
    val checkPassword: Boolean = password == repeatedPassword
    val hasError: Boolean = emailError|| passwordError || repeatedPasswordError || !checkPassword
}
class RegisterStateChange(
    val onEmailChange: (String) -> Unit,
    val onPasswordChange: (String) -> Unit,
    val onRepeatedPasswordChange: (String) -> Unit,
    val onRegister: () -> Unit,
)
