package com.navyliu.widget.unit7Lsn1BradcaseReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class RcReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals("asdfasdf.")){
            intent.getStringExtra("");
        }
    }
}
