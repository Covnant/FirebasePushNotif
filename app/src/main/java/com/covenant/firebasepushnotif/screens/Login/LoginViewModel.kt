package com.covenant.firebasepushnotif.screens.Login

import android.app.Application
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.covenant.firebasepushnotif.MainActivity
import com.covenant.firebasepushnotif.screens.Main.Main
import com.covenant.firebasepushnotif.screens.Main.MainState
import com.covenant.firebasepushnotif.screens.Register.RegisterState
import com.covenant.firebasepushnotif.screens.destinations.MainDestination
import com.covenant.firebasepushnotif.screens.destinations.RegisterDestination
import com.google.firebase.auth.FirebaseAuth
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel(application: Application, private val navigator: DestinationsNavigator): AndroidViewModel(application){
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    val currentUser = firebaseAuth.currentUser
    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    private val _mainState = MutableStateFlow(MainState())
    val mainState = _mainState.asStateFlow()

    fun onLogin(){
        val loginCredentials = loginState.value
        firebaseAuth.signInWithEmailAndPassword(
            loginCredentials.email,
            loginCredentials.password,
        ).addOnCompleteListener { task ->
            if(task.isSuccessful){
                navigator.navigate(MainDestination)
            }
        }
    }

    fun onEmailChange(email: String){
        _loginState.update {
            it.copy(
                email = email
            )
        }
    }

    fun onPasswordChange(password: String){
        _loginState.update {
            it.copy(
                password = password
            )
        }
    }
}