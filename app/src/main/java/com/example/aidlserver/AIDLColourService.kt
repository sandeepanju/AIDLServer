package com.example.aidlserver

import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.os.IBinder
import android.util.Log
import java.util.Random


class AIDLColourService : Service() {

    private val binder = object : IAidlColourInterface.Stub() {
        override fun getColour(): Int {
            val rnd = Random()
            val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
            Log.d(TAG, "getColor: $color")
            return color
        }

        override fun getMessage(): String {
            return "it is connected"
        }

        override fun transformation(dummyUser: DummyUser?): DummyUser {
            return DummyUser(
                name = dummyUser?.name.plus(" transformation"),
                age = dummyUser?.age?.plus(18) ?: 0
            )
        }

    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    private val TAG = "AIDLColourService"
    /*private val binder = object : IAidlColourInterface.Stub() {
        override fun getColour(): Int {
            val rnd = Random()
            val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
            Log.d(TAG, "getColor: $color")
            return color
        }

    }*/
}
