package com.covenant.firebasepushnotif.screens.Register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.covenant.firebasepushnotif.extensions.showLongToast
import com.covenant.firebasepushnotif.extensions.showShortToast
import com.covenant.firebasepushnotif.screens.destinations.LoginDestination
import com.google.firebase.auth.FirebaseAuth
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class RegisterViewModel(private val application: Application,private val navigator: DestinationsNavigator): AndroidViewModel(application){
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _registerState = MutableStateFlow(RegisterState())
    val registerState = _registerState.asStateFlow()


    fun onRegister() {
        var registerState = registerState.value

            if(registerState.email.isBlank())  registerState = registerState.copy(emailError = true)
            if(registerState.password.isBlank())  registerState = registerState.copy(passwordError = true)
            if(registerState.repeatedPassword.isBlank())  registerState = registerState.copy(repeatedPasswordError = true)

            if(!registerState.hasError){
                auth.createUserWithEmailAndPassword(
                    registerState.email,
                    registerState.password,
                ).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        application.showShortToast("Registered Successfully")
                        navigator.navigate(LoginDestination)
                    }
                }.addOnFailureListener {exception->
                    application.showLongToast("Error: ${exception.localizedMessage}")
                }
            }
        _registerState.update { registerState }
    }

    fun onEmailChange(email: String){
        _registerState.update { it.copy(email = email, emailError = false) }
    }

    fun onPasswordChange(password: String){
        _registerState.update { it.copy(password = password, passwordError = false) }
    }

    fun onRepeatedPasswordChange(repeatedPassword: String){
        _registerState.update { it.copy(repeatedPassword = repeatedPassword, repeatedPasswordError = false) }
    }
}