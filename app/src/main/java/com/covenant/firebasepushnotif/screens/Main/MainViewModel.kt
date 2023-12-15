package com.covenant.firebasepushnotif.screens.Main

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.AndroidViewModel
import com.covenant.firebasepushnotif.screens.destinations.LoginDestination
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

class MainViewModel(private val navigator: DestinationsNavigator, application: Application): AndroidViewModel(application) {
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val messaging = Firebase.messaging

    val currentUser = firebaseAuth.currentUser
    val email = currentUser?.email.toString()

    init {
        messaging.token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                Log.d(this::class.java.simpleName, "Token: $token")
            }
        }
        createChannel()
        subscribeToTopic()
    }

    fun onLogout(){
        firebaseAuth.signOut()
        navigator.navigate(LoginDestination)
    }

    private fun createChannel() {
        val notificationChannel = NotificationChannel(
            "main_channel",
            "Main",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            setShowBadge(false)
        }

        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.RED
        notificationChannel.enableVibration(true)
        notificationChannel.description = "Main Channel"

        val notificationManager = getSystemService(getApplication(), NotificationManager::class.java)
        notificationManager?.createNotificationChannel(notificationChannel)
    }

    private fun subscribeToTopic() {
        FirebaseMessaging.getInstance().subscribeToTopic("main")
//            .addOnCompleteListener { task ->
//                if(!task.isSuccessful){
//                    Toast.makeText(this,"Unable to subscribe to topic: ${task.exception?.localizedMessage}",Toast.LENGTH_SHORT).show()
//                    return@addOnCompleteListener
//                }
//                Toast.makeText(this,"Subscribed to topic",Toast.LENGTH_SHORT).show()
//            }
    }

}