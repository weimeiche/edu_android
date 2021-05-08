package com.navyliu.widget.unit8Lsn1Handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.navyliu.widget.R;
import com.navyliu.widget.constants.Constants;

import java.lang.ref.WeakReference;

public class HandlerActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();
    private MyHandler myHandler = new MyHandler();

    private AppCompatTextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        textView = (AppCompatTextView) this.findViewById(R.id.textview);
        textView.setText("====");

        new Thread(new Runnable() {
            @Override
            public void run() {
                textView.setText("Thread");
            }
        }).start();


    }


    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btn_handler:
                Looper.prepare(); //
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        Message message = new Message();
//                        Message message = Message.obtain();
//                        message.what = Constants.GET_HTTP;
//
////                        message.arg1 = 12;
////                        message.arg2 = 112;
//                        message.obj = "hellow word";
//                        myHandler.sendMessage(message);
//                        myHandler.sendMessage()

//                        Message message = Message.obtain(myHandler);
//                        message.what = Constants.GET_HTTP;
////                        message.arg1 = 1;
////                        message.arg1 = 2;
//                        message.obj = "hellow word";
//                        message.sendToTarget();
////                        Message.obtain()

                        Message message = Message.obtain(myHandler, Constants.GET_HTTP, "123");
                        message.sendToTarget();
                    }
                }).start();
                Looper.loop();
                break;
        }
    }



    public class MyHandler extends Handler {

//        WeakReference<Activity> activity;
//        MyHandler(HandlerActivity handlerActivity){
//            activity = <Activity> handlerActivity;
//        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constants.GET_HTTP:
                    Log.d(TAG, "handleMessage: ====" + msg.what + msg.obj);
                    break;
            }
        }
    }


  Runnable runnable =  new Runnable(){

      @Override
      public void run() {

      }
  };

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        myHandler.removeCallbacks(runnable);
    }

    /**
     *
     */
    public class MyTask extends AsyncTask {

        @Override
        protected void onPreExecute() {
            // 执行前需要进行的操作
            super.onPreExecute();
//            String username = edit.gettostring();
//            String password = edit.gettetostring();
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            // 进读条
            super.onProgressUpdate(values);

//            ProgressBar progressBar
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            // 耗时操作
//            更新UI
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            // 发送网络请求
//            HTTPCONTEnt
            super.onPostExecute(o);
        }
    }


}