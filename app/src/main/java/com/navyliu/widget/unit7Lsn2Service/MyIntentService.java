package com.navyliu.widget.unit7Lsn2Service;

import android.Manifest;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.navyliu.widget.MainActivity;
import com.navyliu.widget.R;

import pub.devrel.easypermissions.EasyPermissions;

public class MyIntentService extends IntentService {

    private String TAG = this.getClass().getName();
    private boolean notification_status = false;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public MyIntentService() {
        super("MyIntentService");
    }

    /**
     * 必须要重写的方法
     *
     * @param intent 从Activity/Fragment里面传过来的，里面可以带有参数，根据不同的参数执行不同的任务
     */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String mode = intent.getStringExtra("mode");
        switch (mode) {
            case "0":
                Log.d(TAG, "onHandleIntent: ===========启动 service 0");
                break;
            case "1":
                Log.d(TAG, "onHandleIntent: ===========启动 service 1");
                break;
            case "2":
                Log.d(TAG, "onHandleIntent: ===========启动 service 2");
                notification_status = true;
                break;
            default:
                Log.d(TAG, "onHandleIntent: ===========启动 service 3");
                ;
        }

        Log.d(TAG, "onHandleIntent: ========notification_status=======" + notification_status);
        if (notification_status) {
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            String ID = "com.navyliu.widget";
//            Notification.Builder
            NotificationCompat.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(ID
                        , "channel_intent_service"
                        , manager.IMPORTANCE_HIGH);
                channel.enableLights(true);
                channel.setShowBadge(true);
                channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
                manager.createNotificationChannel(channel);
                builder = new NotificationCompat.Builder(this, ID);
            } else {
                builder = new NotificationCompat.Builder(this, ID);
            }
            builder.setContentIntent(PendingIntent.getActivity(this,
                    0,
                    new Intent(this, MainActivity.class),
                    0));
            builder.setAutoCancel(false);
            builder.setSmallIcon(R.mipmap.navyliu);
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.navyliu));
            builder.setContentTitle("牡丹亭");
            builder.setContentText("原来姹紫嫣红开遍，便似这般都付与断井颓垣。");
            startForeground(1, builder.build());
        }


        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ======notification_status=========" + notification_status);
    }


    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: =================");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void setIntentRedelivery(boolean enabled) {
        Log.d(TAG, "setIntentRedelivery: ===============");
        super.setIntentRedelivery(enabled);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ===================");
        super.onDestroy();
    }
}
