package com.navyliu.widget.unit7Lsn2Service;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * ①首次启动会创建一个Service实例,依次调用onCreate()和onStartCommand()方法,此时Service 进入运行状态,\
 *      如果再次调用StartService启动Service,将不会再创建新的Service对象
 *      , 系统会直接复用前面创建的Service对象,调用它的onStartCommand()方法！
 * ②但这样的Service与它的调用者无必然的联系,就是说当调用者结束了自己的生命周期
 *      , 但是只要不调用stopService,那么Service还是会继续运行的!
 * ③无论启动了多少次Service,只需调用一次StopService即可停掉Service
 */
public class MyService extends Service {

    private final String TAG = this.getClass().getName();
    public static final String ACTION = "com.navyliu.widget.test_service";

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ===========");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ===========");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ================");
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ============");
        return super.onStartCommand(intent, flags, startId);
    }

    
}
