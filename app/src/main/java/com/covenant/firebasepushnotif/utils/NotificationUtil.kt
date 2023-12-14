package com.covenant.firebasepushnotif.utils

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import com.covenant.firebasepushnotif.MainActivity
import com.covenant.firebasepushnotif.R

private const val NOTIFICATION_ID = 8989

@SuppressLint("UnspecifiedImmutableFlag")
fun NotificationManager.sendNotification(messageBody: String, messageTitle: String, applicationContext: Context) {
    val contentIntent = Intent(applicationContext, MainActivity::class.java)

    val pendingIntent: PendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        PendingIntent.getActivity(
            applicationContext,
            NOTIFICATION_ID,
            contentIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    } else {
        PendingIntent.getActivity(
            applicationContext,
            NOTIFICATION_ID,
            contentIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
    }


    val aufImage = BitmapFactory.decodeResource(
        applicationContext.resources,
        R.drawable.firebase_banner
    )

    val bigPictureStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(aufImage)
//        .bigLargeIcon(null)

    val builder = NotificationCompat.Builder(
        applicationContext,
        "main_channel"
    )
        .setSmallIcon(R.drawable.firebase_logo)
        .setContentTitle(messageTitle)
        .setContentText(messageBody)
        .setContentIntent(pendingIntent)
        .setStyle(bigPictureStyle)
        .setLargeIcon(aufImage)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setAutoCancel(true)

    notify(NOTIFICATION_ID,builder.build())
}