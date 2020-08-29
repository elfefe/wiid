package com.elfefe.wiid.utility

import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.elfefe.wiid.controllers.BaseApplication

fun checkPermissions(activity: AppCompatActivity, vararg permissions: String): Boolean {
    permissions.forEach {
        if (ActivityCompat.checkSelfPermission(
                activity,
                it
            ) != PackageManager.PERMISSION_GRANTED
        ) ActivityCompat.requestPermissions(
            activity,
            permissions,
            1
        )
        else return true
    }
    return false
}

val preferences: SharedPreferences = BaseApplication.INSTANCE.getSharedPreferences(PreferenceUtils.KEY, Context.MODE_PRIVATE)