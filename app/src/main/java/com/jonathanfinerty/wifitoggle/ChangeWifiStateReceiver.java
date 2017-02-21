package com.jonathanfinerty.wifitoggle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;

public class ChangeWifiStateReceiver extends BroadcastReceiver {

    private static final String TAG = ChangeWifiStateReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        if (intent.getExtras() == null) {
            Log.e(TAG, "Error, expecting 'enabled' boolean extra on broadcast");
            return;
        }
        
        Object enabledExtra = intent.getExtras().get("enabled");

        if (enabledExtra == null) {
            Log.e(TAG, "Error, expecting 'enabled' boolean extra on broadcast");
            return;
        }

        if (!(enabledExtra instanceof Boolean)) {
            Log.e(TAG, "Error, 'enabled' extra should be boolean");
            return;
        }

        boolean enabled = (Boolean) enabledExtra;

        String msg;
        if (enabled) {
            msg = "Turning wifi on";
        } else {
            msg = "Turning wifi off";
        }

        Log.i(TAG, msg);

        wifiManager.setWifiEnabled(enabled);
    }
}
