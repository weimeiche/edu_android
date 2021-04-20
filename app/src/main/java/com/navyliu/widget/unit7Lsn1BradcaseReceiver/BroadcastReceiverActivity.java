package com.navyliu.widget.unit7Lsn1BradcaseReceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.navyliu.widget.R;

import java.util.zip.Inflater;

import pub.devrel.easypermissions.EasyPermissions;

public class BroadcastReceiverActivity extends AppCompatActivity {

    private InternetReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);

        registerInternetBroadcasetReceiver();

//        sendBroadcast(new Intent("com.navyliu.widget.myreceiver"));

//        Intent intent = new Intent(this, CountdownService.class);
//        startService(intent)
        Intent itCountdown = new Intent(this, CountdownService.class);
        startService(itCountdown);
    }

    private void registerInternetBroadcasetReceiver() {
        // 动态注册广播
        receiver = new InternetReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(receiver, filter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消广播
        unregisterReceiver(receiver);
    }
}