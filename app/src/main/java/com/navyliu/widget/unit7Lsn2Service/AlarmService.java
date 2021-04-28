package com.navyliu.widget.unit7Lsn2Service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;

public class AlarmService extends Service {

    private final String TAG = this.getClass().getName();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 这里开辟一条线程，用来执行具体的逻辑操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: ==================");
            }
        }).start();

        /**
         * Step 1：获得Service: AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
         * Step 2：通过set方法设置定时任务 int anHour = 2 * 1000;
         *                              long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
         *                              manager.set(AlarmManager.RTC_WAKEUP,triggerAtTime,pendingIntent);
         * Step 3：定义一个Service 在onStartCommand中开辟一条事务线程,用于处理一些定时逻辑
         * Step 4：定义一个Broadcast(广播)，用于启动Service 最后别忘了，在AndroidManifest.xml中对这Service与Boradcast进行注册！
         *
         * PS:
         * Timer类与Alarm机制区别：
         *      CPU一旦休眠，Timer中的定时任务 就无法运行；Alarm则不存在这种情况，他具有唤醒CPU的功能
         */
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        // 定时, 单位ms（毫秒）
        int cd = 10 * 1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + cd;
        Intent ai = new Intent(this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, ai, 0);
        /**
         * TYPE:
         * AlarmManager.ELAPSED_REALTIME: 闹钟在手机睡眠状态下不可用，该状态下闹钟使用相对时间（相对于系统启动开始），状态值为3;
         * AlarmManager.ELAPSED_REALTIME_WAKEUP 闹钟在睡眠状态下会唤醒系统并执行提示功能，该状态下闹钟也使用相对时间，状态值为2；
         * AlarmManager.RTC 闹钟在睡眠状态下不可用，该状态下闹钟使用绝对时间，即当前系统时间，状态值为1；
         * AlarmManager.RTC_WAKEUP 表示闹钟在睡眠状态下会唤醒系统并执行提示功能，该状态下闹钟使用绝对时间，状态值为0;
         * AlarmManager.POWER_OFF_WAKEUP 表示闹钟在手机关机状态下也能正常进行提示功能，所以是5个状态中用的最多的状态之一
         *                  ， 该状态下闹钟也是用绝对时间，状态值为4；不过本状态好像受SDK版本影响，某些版本并不支持；
         */
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);

        return super.onStartCommand(intent, flags, startId);
    }






















    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
