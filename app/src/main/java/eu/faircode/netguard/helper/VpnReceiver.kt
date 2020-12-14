package eu.faircode.netguard.helper

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.VpnService
import androidx.preference.PreferenceManager
import eu.faircode.netguard.ServiceSinkhole

class VpnReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            if (it.hasExtra("status")) {
                val status = it.getStringExtra("status")
                val prepare = VpnService.prepare(context)
                if (prepare == null) {
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
                } else {
                    if (status == "start") {
//                        val intentActivityMain = Intent(context, ActivityMain::class.java)
//                        intentActivityMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                        intentActivityMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//                        intentActivityMain.putExtra(ActivityMain.EXTRA_ASK_PERMISSION, "permissions")
//                        context!!.startActivity(intentActivityMain)

                        val broadcastIntent = Intent()
                        broadcastIntent.action = "com.cando.chatsie"
                        broadcastIntent.putExtra("status", "start")

                        broadcastIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
                        broadcastIntent.component =
                                ComponentName("com.cando.chatsie", "com.cando.chatsie.receiver.ResponseVPNReceiver")
                        context!!.sendBroadcast(broadcastIntent)
                    }
                }
            }
        }

    }
}