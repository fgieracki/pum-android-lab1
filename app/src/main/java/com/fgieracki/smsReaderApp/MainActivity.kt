package com.fgieracki.smsReaderApp

import SMSReaderApp
import android.Manifest
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.fgieracki.smsReaderApp.ui.theme.SmsReaderAppTheme

class MainActivity : ComponentActivity() {
    private val smsViewModel: SmsViewModel by viewModels()
    private lateinit var batteryReceiver: BroadcastReceiver

    private val smsPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Toast.makeText(this, getString(R.string.sms_permission_granted), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, getString(R.string.sms_permission_denied), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: add missing permissions for receiving SMS messages and uncomment this code
//        if (ActivityCompat.checkSelfPermission(this, <MISSING PERMISSION>)
//            != PackageManager.PERMISSION_GRANTED
//        ) {
//            smsPermissionLauncher.launch(<MISSING PERMISSION>)
//        }

        val smsReceiver = SmsReceiver { message ->
            smsViewModel.addSms(message)
        }
        registerReceiver(smsReceiver, IntentFilter("android.provider.Telephony.SMS_RECEIVED"))

        batteryReceiver = DoNotTouch()
        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(batteryReceiver, filter)

        setContent {
            SMSReaderApp(smsViewModel = smsViewModel)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(batteryReceiver)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SmsReaderAppTheme {
        Column(modifier = Modifier.padding(16.dp)) {
        Greeting("Android")
        }
    }
}