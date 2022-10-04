package com.navyliu.widget.unit7Lsn1BradcaseReceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.navyliu.widget.R;
import com.navyliu.widget.unit7Lsn1BradcaseReceiver.test.CountdownService;

public class BroadcastReceiverActivity extends AppCompatActivity {

    private InternetReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);

        registerInternetBroadcasetReceiver();
    }

    /**
     * 动态注册广播：
     * 1、实例化广播接收者；
     * 2、实例化IntentFilter，添加行为；
     * 3、使用registerReceiver方法注册广播；
     * 4、注意适时注销广播.  eg:unregisterReceiver(receiver);
     */
    private void registerInternetBroadcasetReceiver() {
        // 动态注册广播
        receiver = new InternetReceiver();
        IntentFilter filter = new IntentFilter();
        // 网络改变的系统广播
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(receiver, filter);
    }


    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btn_send_broadcast: // 自定义广播
                customBroadcast();
                break;
            case R.id.btn_local: // 本地广播
                localBroadcast();
                break;
        }
    }

    /**
     * 本地广播
     */
    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;
    private IntentFilter intentFilter;
    private void localBroadcast() {
        /**
         * 注册本地广播  开始
         */
        // 初始化管理器
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        // 初始化广播接收者
        localReceiver = new LocalReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.navyliu.widget.local_receiver");
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);
        /**
         * 注册本地广播  结束
         */

        // 发送本地广播
        Intent intent = new Intent("com.navyliu.widget.local_receiver");
        localBroadcastManager.sendBroadcast(intent);
    }

    /**
     * 自定义广播 ，静态注册
     * 1、在Androidmanifest.xml中注册receiver；
     * 2、实例化一个行为和在Androidmanifest中的接收者行为一致的Intent
     * 3、通过setComponent设置intent的组件
     *      参数一: 广播接收器应用的包名 ，如："com.navyliu.widget"
     *      参数二： 广播接收器所在的完整路径， 如："com.navyliu.widget.unit7Lsn1BradcaseReceiver.MyReceiver"
     * 4、（可选）使用put方法写入参数；
     * 5、通过sendBroadcast发送广播；
     * 6、在广播中接收参数
     */
    private void customBroadcast() {
//        android.net.conn. BOOT_COMPLATE
//        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        Intent intent = new Intent("com.navyliu.widget.myreceiver");
        // 参数一: 广播接收器应用的包名 ，如："com.navyliu.widget"
        // 参数二： 广播接收器所在的完整路径， 如："com.navyliu.widget.unit7Lsn1BradcaseReceiver.MyReceiver"
        intent.setComponent(new ComponentName("com.navyliu.widget"
                , "com.navyliu.widget.unit7Lsn1BradcaseReceiver.MyReceiver"));
        // 携带参数
        intent.putExtra("test", "我是来测 A 应用的Android 8.0 系统静态广播的测试数据");
        // 发送广播
        sendBroadcast(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消广播
        unregisterReceiver(receiver);
        // 注销本地广播
        localBroadcastManager.unregisterReceiver(localReceiver);
    }
}