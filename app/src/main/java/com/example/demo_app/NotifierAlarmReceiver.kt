package com.example.demo_app

import android.annotation.SuppressLint
import android.app.*
import android.content.BroadcastReceiver
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService

class NotifierAlarmReceiver : BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {
        StringBuilder().apply {
            if (intent != null) {
                append("Action: ${intent.action}\n")
            }
            if (intent != null) {
                append("URI: ${intent.toUri(Intent.URI_INTENT_SCHEME)}\n")
            }
            toString().also { log ->
                Log.d(TAG, log)
                Toast.makeText(context, log, Toast.LENGTH_LONG).show()
            }
        }
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        Toast.makeText(context, "log", Toast.LENGTH_LONG).show()
        val task = TaskStackBuilder.create(context)
        task.addParentStack(MainActivity::class.java)
        task.addNextIntent(intent)

        val notification = Notification.Builder(context)
            .setChannelId("alarm_01")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setAutoCancel(true)
            .setContentTitle("kek")
            .setContentText("lol kek")
            .setContentIntent(pendingIntent)
            .build();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("alarm_01", "alarm_notification", NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = "descriptionText"
            }
            // Register the channel with the system
            val notificationManager: NotificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
            notificationManager.notify(0, notification)
        }
    }

}