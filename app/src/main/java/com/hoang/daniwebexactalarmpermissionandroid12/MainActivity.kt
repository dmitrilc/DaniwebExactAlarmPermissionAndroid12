package com.hoang.daniwebexactalarmpermissionandroid12

import android.Manifest
import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED

private const val TAG = "MAIN_ACTIVITY"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            checkScheduleExactAlarmPermWrongWay()
            checkScheduleExactAlarmPermCorrectWay()

            val alarmPermissionIntent = Intent(
                Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM,
                Uri.parse("package:com.hoang.daniwebexactalarmpermissionandroid12")
            )
            startActivity(alarmPermissionIntent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun checkScheduleExactAlarmPermWrongWay(){
        val isGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.SCHEDULE_EXACT_ALARM)
        Log.d(TAG, "Has permission? ${isGranted == PERMISSION_GRANTED}")
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun checkScheduleExactAlarmPermCorrectWay(){
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        Log.d(TAG, "Has permission (correct method)? ${alarmManager.canScheduleExactAlarms()}")
    }
}