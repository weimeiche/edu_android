package com.navyliu.widget.unit7Lsn2Service;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.Manifest;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.navyliu.widget.MainActivity;
import com.navyliu.widget.R;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import pub.devrel.easypermissions.EasyPermissions;

public class ServiceActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();

    private AppCompatEditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        edit = (AppCompatEditText) this.findViewById(R.id.edit);
        
        
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 输入内容前的回调
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 输入框内容改变时的回调
            }

            @Override
            public void afterTextChanged(Editable s) {
                // 输入框内容改变以后的回调
                String str = s.toString(); // 输入框中的内容
            }
        });
//        Intent intent = new Intent(this, TestService.class);
//        startService(intent);
//        startThread();
    }

    /**
     * 开启新进程  开始
     */
    private void startThread() {
        //  继承Thread类
        ExtendsThread thread = new ExtendsThread();
        thread.start();

        // 实现 Runnable 接口
        RunableThread runableThread = new RunableThread();
        new Thread(runableThread).start();

        // runable匿名方法
        new Thread(new Runnable() {
            @Override
            public void run() {
            }
        }).start();

        // 实现Callable接口
        // 1、实例callable继承类的对象
        // 2 、将对象放一个异步任务里面；
        // 3、将任务放到Thread里面；
        // 4、使用start方法启动线程；
        CallableThread callableThread = new CallableThread();
        FutureTask futureTask = new FutureTask(callableThread);
        Thread callable = new Thread(futureTask);
        callable.start();
    }


    public class CallableThread implements Callable {

        @Override
        public Object call() throws Exception {
            Log.d(TAG, "call: =============CallableThread implements Callable===========");
            // 耗时操作。比如网络请求
            return null;
        }
    }

    public class RunableThread implements Runnable {

        @Override
        public void run() {
            Log.d(TAG, "run: =======RunableThread implements Runnable=======");
//            网络请求
        }
    }

    public class ExtendsThread extends Thread {
        @Override
        public void run() {
            super.run();
            Log.d(TAG, "run: ====ExtendsThread extends Thread===========");
            // 要执行的动作
        }
    }

    /**
     * 开始新进程  结束
     * @param view
     */


    public void onclick(View view) {
        Intent serviceIntent = new Intent(this, MyService.class);


        Intent intent = new Intent(this, MyIntentService.class);
        String mode = edit.getText().toString();
        intent.putExtra("mode", mode);



        switch (view.getId()) {
            case R.id.btn_start: // 开启服务
                startService(serviceIntent);
                break;
            case R.id.btn_stop: // 停止服务
                stopService(serviceIntent);
                break;
            // 启动/停止bindler服务
            case R.id.btn_start_binlder:
                binderService(1);
                break;
            case R.id.btn_stop_bindler:
                binderService(0);
                break;
            case R.id.btn_bindler: // 获取bindler的状态
                getBinderStatus();
                break;
            case R.id.btn_start_intent_service: // 启动intentService
                String[] permissions = {Manifest.permission.FOREGROUND_SERVICE};
                if (EasyPermissions.hasPermissions(this, permissions)) {
                    startService(intent);
                } else {
                    EasyPermissions.requestPermissions(this,
                            "你必须开这个权限，否则我保证不打死你"
                            , 1
                            , permissions);
                }
                break;
            case R.id.btn_stop_intent_service: // 停止 IntentService
                stopService(intent);
                break;

            case R.id.btn_alarm: // 定时服务
                Intent alarmIntent = new Intent(this, AlarmService.class);
                startService(alarmIntent);
                break;
        }
    }


    private void getBinderStatus() {
        Log.d(TAG, "getBinderStatus: ======" + binder.getCount());
    }


    // 保持所启动Service的IBinder对象，同时定义一个ServiceConnection对象
    private MyBindService.MyBinder binder;
    private ServiceConnection connection = new ServiceConnection() {
        // Activity与Service断开连接时回调方法
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: ==========");
            binder = (MyBindService.MyBinder) service;
        }

        // Activity与Service断开连接时回调方法
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: =============");
        }
    };

    /**
     * 服务的两种启动方式：
     * 1、startService  停止服务：stopService
     * 2、bindService   取消绑定： unbindService
     */
    private void binderService(int mode) {
        Intent intent = new Intent(this, MyBindService.class);
        switch (mode) { // 0: 停止；1：启动；
            case 0:
                unbindService(connection);
                break;
            case 1:
                bindService(intent, connection, Service.BIND_AUTO_CREATE);
//                bindService()
                break;
        }
    }

}