package com.navyliu.widget.unit7Lsn1BradcaseReceiver.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootCompleteReceiver extends BroadcastReceiver {

    private final String ACTION_BOOT = "com.navyliu.widget.boot_complete";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ACTION_BOOT.equals(intent.getAction())) {
            Toast.makeText(context, "开机完毕", Toast.LENGTH_LONG).show();
        }
    }
}
