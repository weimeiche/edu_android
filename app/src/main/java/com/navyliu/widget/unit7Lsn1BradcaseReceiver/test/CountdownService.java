package com.navyliu.widget.unit7Lsn1BradcaseReceiver.test;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;


import com.navyliu.widget.constants.Constants;
import com.navyliu.widget.constants.TimeConstants;

import org.json.JSONException;
import org.json.JSONObject;


public class CountdownService extends Service {

    private final String TAG = this.getClass().getName();
    private MyHandler myHandler = new MyHandler();

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            myHandler.removeCallbacks(runnable);
            myHandler.postDelayed(runnable, 1000);
            Log.d(TAG, "onReceive: =======CountdownService  中的BroadcastReceiver注册成功=============");
        }
    };


    /**
     * todo 监听返回消息
     **/
    @SuppressLint("HandlerLeak")
    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constants.GET_HTTP: // 获取服务器时间成功
                    getJson(msg.obj.toString());
                    break;
            }
        }
    }


    /**
     * 解析从服务器返回的时间
     *
     * @param jsonStr
     */
    private void getJson(String jsonStr) {
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            if ("SUCCESS".equals(jsonObject.getString("flag"))) {
                TimeConstants.setCurr_date(jsonObject.getString("date"));
                TimeConstants.setCurr_year(jsonObject.getString("year"));
                TimeConstants.setCurr_month(jsonObject.getString("month"));
                TimeConstants.setCurr_day(jsonObject.getString("day"));
                TimeConstants.setCurr_hour(Integer.parseInt(jsonObject.getString("hour")));
                TimeConstants.setCurr_min(Integer.parseInt(jsonObject.getString("min")));
                TimeConstants.setCurr_sec(Integer.parseInt(jsonObject.getString("sec")));
            }
//            LogUtils.d("curr datetime is===" + TimeConstants.getCurr_sec());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * todo 返回24小时制的时间
     *
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    private void get24HourDiff() {
        TimeConstants.setCurr_sec(TimeConstants.getCurr_sec() + 1);
        if (TimeConstants.getCurr_sec() == 60) {
            TimeConstants.setCurr_sec(0);
            TimeConstants.setCurr_min(TimeConstants.getCurr_min() + 1);
            // 获取服务器时间
//            GetServerTimeThread timeThread = new GetServerTimeThread();
//            timeThread.start();
        }
        if (TimeConstants.getCurr_min() == 60) {
            TimeConstants.setCurr_min(0);
            TimeConstants.setCurr_hour(TimeConstants.getCurr_hour() + 1);
        }
        if (TimeConstants.getCurr_hour() == 24) {
            TimeConstants.setCurr_hour(0);
        }
        // 倒计时+1
        int countdownCount = TimeConstants.getCountdown_sec();
        countdownCount = countdownCount > 1000 ? 95 : ++countdownCount;
        TimeConstants.setCountdown_sec(countdownCount);
//		LogUtils.d("countdownCount:---"+countdownCount);
        Log.d(TAG, "countdownCount: ===========" + countdownCount);
    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            myHandler.postDelayed(this, 1000);
            initCountdown();
        }
    };


    private void initCountdown() {
        get24HourDiff();
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: =======CountdownService  onCreate启动成功=============");
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver, mFilter);
    }
//
//    /**
//     * 获取服务器时间
//     */
//    private class GetServerTimeThread extends Thread {
//        public void run() {
//            super.run();
//            OkHttpUtils.post().url(URLs.URL_SERVER_TIME)
//                    .addParams("user_id", FreeGiftApp.getUserId())
//                    .addParams("android_id", DeviceUtils.getAndroidID())
//                    .build()
//                    .execute(new StringCallback() {
//                        @Override
//                        public void onError(Call call, Exception e, int id) {
//
//                        }
//
//                        @Override
//                        public void onResponse(String response, int id) {
//                            Message message = Message.obtain(myHandler, IConstants.GET_HTTP, response.trim());
//                            message.sendToTarget();
//                        }
//                    });
//        }
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

}
