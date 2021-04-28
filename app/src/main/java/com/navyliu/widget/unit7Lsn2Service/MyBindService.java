package com.navyliu.widget.unit7Lsn2Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;


/**
 * onCreate()：当Service第一次被创建后立即回调该方法，该方法在整个生命周期 中只会调用一次！
 * onDestory()：当Service被关闭时会回调该方法，该方法只会回调一次！
 * onStartCommand(intent,flag,startId)：早期版本是onStart(intent,startId), 当客户端调用startService(Intent)方法时会回调，可多次调用StartService方法， 但不会再创建新的Service对象，而是继续复用前面产生的Service对象，但会继续回调 onStartCommand()方法！
 * IBinder onOnbind(intent)：该方法是Service都必须实现的方法，该方法会返回一个 IBinder对象，app通过该对象与Service组件进行通信！
 * onUnbind(intent)：当该Service上绑定的所有客户端都断开时会回调该方法！
 * <p>
 * <p>
 * <p>
 * ①当首次使用bindService绑定一个Service时,系统会实例化一个Service实例,并调用其onCreate()和onBind()方法,然后调用者就可以通过IBinder和Service进行交互了,此后如果再次使用bindService绑定Service,系统不会创建新的Sevice实例,也不会再调用onBind()方法,只会直接把IBinder对象传递给其他后来增加的客户端!
 * ②如果我们解除与服务的绑定,只需调用unbindService(),此时onUnbind和onDestory方法将会被调用!这是一个客户端的情况,假如是多个客户端绑定同一个Service的话,情况如下 当一个客户完成和service之间的互动后，它调用 unbindService() 方法来解除绑定。当所有的客户端都和service解除绑定后，系统会销毁service。（除非service也被startService()方法开启）
 * ③另外,和上面那张情况不同,bindService模式下的Service是与调用者相互关联的,可以理解为 "一条绳子上的蚂蚱",要死一起死,在bindService后,一旦调用者销毁,那么Service也立即终止!
 * 通过BindService调用Service时调用的Context的bindService的解析 bindService(Intent Service,ServiceConnection conn,int flags)
 * service:通过该intent指定要启动的Service
 * conn:ServiceConnection对象,用户监听访问者与Service间的连接情况, 连接成功回调该对象中的onServiceConnected(ComponentName,IBinder)方法; 如果Service所在的宿主由于异常终止或者其他原因终止,导致Service与访问者间断开 连接时调用onServiceDisconnected(CompanentName)方法,主动通过unBindService() 方法断开并不会调用上述方法!
 * flags:指定绑定时是否自动创建Service(如果Service还未创建), 参数可以是0(不自动创建),BIND_AUTO_CREATE(自动创建)
 */
public class MyBindService extends Service {

    private String TAG = this.getClass().getName();
    private int count = 0;
    private boolean quit;

    /**
     * 定义OnBinder方法所返回的对象
     */
    private MyBinder binder = new MyBinder();

    public class MyBinder extends Binder {
        public int getCount() {
            return count;
        }
//        public
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: =========");
        // 创建一个线程动态修改count
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (!quit) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                }
            }
        };
        new Thread(runnable).start();

    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: =============================");
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ============================");
        return super.onUnbind(intent);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: =============================");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.quit = true;
        Log.d(TAG, "onDestroy: ============");
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, "onRebind: ============");
        super.onRebind(intent);
    }
}
