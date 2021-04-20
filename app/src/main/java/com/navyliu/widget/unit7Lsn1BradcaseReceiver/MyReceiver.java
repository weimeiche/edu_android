package com.navyliu.widget.unit7Lsn1BradcaseReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

class MyReceiver extends BroadcastReceiver {

    private final String TAG = this.getClass().getName();
    private final String ACTION_BOOT = "com.navyliu.widget.myreceiver";
    
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: ======com.navyliu.widget.myreceiver=");
    }
}
