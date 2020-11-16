package eu.faircode.netguard.helper

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import eu.faircode.netguard.ServiceSinkhole

class VpnReceiver : BroadcastReceiver() {
    //    var prefs = MainPrefs()
    override fun onReceive(context: Context?, intent: Intent?) {

        Log.d("VpnLog", "VpnReceiver")

        intent?.let {
            if (it.hasExtra("status")) {
                val status = it.getStringExtra("status")
                if (status == "start") {
                    ServiceSinkhole.start("from receiver", context)

                } else if (status == "stop") {
                    ServiceSinkhole.stop("from receiver", context, false)
                }
            }
        }

    }

//          intent?.let {
//            if(it.hasExtra("batteryPercentage")){
//                val batteryPercentage = it.getStringExtra("batteryPercentage")
//                prefs.setBatteryLevel(batteryPercentage)
//                val batteryCharge = it.getBooleanExtra("batteryCharge",false)
//                Log.d("LogBatSMS","isCharging $batteryCharge")
//
//                prefs.setBatteryStatus(batteryCharge)
//                EventBus.getDefault().post(BatteryLevelChangedEvent(batteryPercentage.toInt(),batteryCharge))
//
//            }
//
//            if(it.hasExtra("gsmData")){
//                val gsmData  = it.getStringExtra("gsmData")
//                prefs.setGSM(gsmData)
//                EventBus.getDefault().post(GSMLevelChangedEvent(gsmData))
//
//            }
//        }
}