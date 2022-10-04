package com.navyliu.widget.unit7Lsn1BradcaseReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * 定义接收者
 * 1、创建一个类，
 *      a、可以手动新建一个class类，继承于BroadcastReceiver，然后实现抽象方法onReceive
 *      b、可以直接new->other->Broadcast Receiver
 * 2、在广播中接收intent中的参数，并进行对应的处理
 *      a、intent.getExtras()或者intent.getStringExtra(参数)   来接收参数；
 *      b、intent.getAction()  判断广播的行为，从而判断是否属于自己需要的广播，是否需要处理
 */
public class MyReceiver extends BroadcastReceiver {

    private final String TAG = this.getClass().getName();
    private final String ACTION_BOOT = "com.navyliu.widget.myreceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle extras = intent.getExtras();
        String message = intent.getStringExtra("test");
        Log.i("MyBroadCastReciver"," -- 接收到的消息 -- = "+message);

        Log.d(TAG, "onReceive: ======com.navyliu.widget.myreceiver=");
        if (ACTION_BOOT.equals(intent.getAction())) {
            Log.d(TAG, "onReceive: ============" + ACTION_BOOT);
        }
    }
}
