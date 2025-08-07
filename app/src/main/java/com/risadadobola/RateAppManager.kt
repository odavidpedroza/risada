package com.risadadobola

import android.content.Context
import android.content.Intent
import android.net.Uri

class RateAppManager(private val context: Context) {
    fun rateApp() {
        val appPackageName = context.packageName
        try {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)))
        } catch (e: android.content.ActivityNotFoundException) {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)))
        }
    }
}
