package com.covenant.firebasepushnotif.screens.Login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.covenant.firebasepushnotif.extensions.showLongToast
import com.covenant.firebasepushnotif.extensions.showShortToast
import com.covenant.firebasepushnotif.screens.destinations.MainDestination
import com.google.firebase.auth.FirebaseAuth
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel(private val application: Application, private val navigator: DestinationsNavigator): AndroidViewModel(application){
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()


    fun onLogin(){
        var state = loginState.value

        if(state.email.isBlank()) state = state.copy(hasEmailError = true)
        if(state.password.isBlank()) state = state.copy(hasPasswordError = true)

        if(!state.hasError){
            firebaseAuth.signInWithEmailAndPassword(
                state.email,
                state.password,
            ).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    application.showShortToast("Login Successful")
                    navigator.navigate(MainDestination)
                }
            }.addOnFailureListener {exception ->
                application.showLongToast("Error: ${exception.message}")
            }
        }
        _loginState.update { state }
    }

    fun onEmailChange(email: String){
        _loginState.update {
            it.copy(
                email = email,
                hasEmailError = false,
            )
        }
    }

    fun onPasswordChange(password: String){
        _loginState.update {
            it.copy(
                password = password,
                hasPasswordError = false,
            )
        }
    }
}