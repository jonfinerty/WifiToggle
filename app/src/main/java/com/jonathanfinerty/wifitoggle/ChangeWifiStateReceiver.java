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
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (intent.getExtras() == null) {
            log("Error, expecting 'enabled' boolean extra on broadcast");
            return;
        }

        Object enabledExtra = intent.getExtras().get("enabled");

        if (enabledExtra == null) {
            log("Error, expecting 'enabled' boolean extra on broadcast");
            return;
        }

        if (!(enabledExtra instanceof Boolean)) {
            log("Error, 'enabled' extra should be boolean");
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

    private void log(String message) {
        final String exampleUsage = "Example Usage: adb shell am broadcast -a WifiChange -n com.jonathanfinerty.wifitoggle/.ChangeWifiStateReceiver --ez enabled true";
        Log.e(TAG, message);
        Log.e(TAG, exampleUsage);
    }
}
