package com.fgieracki.smsReaderApp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.SmsMessage
import android.util.Log

class SmsReceiver(private val onSmsReceived: (String) -> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            val messages: Array<SmsMessage> = Telephony.Sms.Intents.getMessagesFromIntent(intent)

            for (message in messages) {
                val phoneNumber = message.displayOriginatingAddress
                val messageBody = message.messageBody
                val fullMessage = "SMS from $phoneNumber: $messageBody"

                Log.d("SmsReceiver", fullMessage)

                onSmsReceived(fullMessage)
            }
        }
    }
}