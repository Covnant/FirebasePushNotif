package com.covenant.firebasepushnotif.screens.Register

import androidx.lifecycle.ViewModel
import com.covenant.firebasepushnotif.screens.destinations.LoginDestination
import com.covenant.firebasepushnotif.screens.destinations.RegisterDestination
import com.google.firebase.auth.FirebaseAuth
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class RegisterViewModel(private val navigator: DestinationsNavigator): ViewModel(){
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _registerState = MutableStateFlow(RegisterState())
    val registerState = _registerState.asStateFlow()


    fun onRegister() {
        val registerState = registerState.value
        auth.createUserWithEmailAndPassword(
            registerState.email,
            registerState.password,
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                navigator.navigate(LoginDestination)
            }
        }
    }

    fun onEmailChange(email: String){
        _registerState.update { it.copy(email = email) }
    }

    fun onPasswordChange(password: String){
        _registerState.update { it.copy(password = password) }
    }

    fun onRepeatedPasswordChange(repeatedPassword: String){
        _registerState.update { it.copy(repeatedPassword = repeatedPassword) }
    }
}