package com.example.flightmanager.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class Receiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
      val intent= Intent(context,Service::class.java)
        context?.startService(intent)
    }
}