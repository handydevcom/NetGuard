package eu.faircode.netguard.helper

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.preference.PreferenceManager
import eu.faircode.netguard.ServiceSinkhole

class VpnReceiver : BroadcastReceiver() {
    //    var prefs = MainPrefs()
    override fun onReceive(context: Context?, intent: Intent?) {

        Log.d("VpnLog", "VpnReceiver")

        intent?.let {
            if (it.hasExtra("status")) {
                val status = it.getStringExtra("status")
                if (status == "start") {
                    val prefs = PreferenceManager.getDefaultSharedPreferences(context)
                    if (prefs.getBoolean("enabled", false)) {
                        ServiceSinkhole.reload("from receiver", context, false)
                    } else {
                        ServiceSinkhole.start("from receiver", context)
                    }

                } else if (status == "stop") {
                    ServiceSinkhole.stop("from receiver", context, false)
                }
            }
        }

    }
}