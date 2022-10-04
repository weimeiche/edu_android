package com.navyliu.widget.unit7Lsn1BradcaseReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

class InternetReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "网络状态发生变化。。。", Toast.LENGTH_LONG).show();
    }
}
