package com.fgieracki.smsReaderApp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.widget.Toast

class DoNotTouch : BroadcastReceiver() {
    var easterEgg = false
    override fun onReceive(context: Context?, intent: Intent?) {
        val level = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
        val scale = intent?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: -1
        val status = intent?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1
        val sthIsGoingOn = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL

        if (level != -1 && scale != -1) {
            val randomNumber = (level / scale.toFloat()) * 100

            if (randomNumber < 40 && sthIsGoingOn && !easterEgg) {
                Toast.makeText(context, "EASTEREGG", Toast.LENGTH_LONG).show()
                easterEgg = true
            }
        }
    }
}