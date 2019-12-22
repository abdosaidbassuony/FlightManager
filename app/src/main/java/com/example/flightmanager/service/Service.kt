package com.example.flightmanager.service

import android.R
import android.app.IntentService
import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.flightmanager.view.broadPass.BoardPassActivity


class Service:IntentService("NotificationService") {
    val CHANNELID = "CHANNELID"
    override fun onHandleIntent(intent: Intent?) {
        val builder = NotificationCompat.Builder(this,CHANNELID)
        builder.setContentTitle("My Title")
        builder.setContentText("This is the Body")
        builder.setSmallIcon(R.drawable.ic_dialog_alert)
        val notifyIntent = Intent(this, BoardPassActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        builder.setContentIntent(pendingIntent)
        val notificationCompat: Notification = builder.build()
        val managerCompat = NotificationManagerCompat.from(this)
        managerCompat.notify(1, notificationCompat)
    }

}