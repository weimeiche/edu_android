package com.navyliu.widget.unit8Lsn3Http;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.navyliu.widget.R;
import com.navyliu.widget.constants.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.lang.ref.WeakReference;

import okhttp3.Call;

public class HttpActivity extends AppCompatActivity {

    private AppCompatImageView getImg;

    private String url_img = "https://bkimg.cdn.bcebos.com/pic/faf2b2119313b07e06eff2050fd7912397dd8c69?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto";
    //    private String url_html = "https://developer.android.google.cn/";
//    private String url_html = "http://192.168.1.102:8080/chicken/wx/public/city.php";
//    private String url_city = "http://192.168.1.102:8080/chicken/wx/public/city.php";
//    private String url_html = "http://172.31.34.7:8080/chicken/wx/public/city.php";
//    private String url_city = "http://172.31.34.7:8080/chicken/wx/public/city.php";
    private String url_html = "http://192.168.43.218:8080/chicken/wx/public/city.php";
    private String url_city = "http://192.168.43.218:8080/chicken/wx/public/city.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);

        findView();
        myHandler = new MyHandler(this);

    }

    private MyHandler myHandler;
    private final String TAG = this.getClass().getName();

    private class MyHandler extends Handler {
        private WeakReference<HttpActivity> activity;

        public MyHandler(HttpActivity mActivity) {
            this.activity = new WeakReference<>(mActivity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case Constants.GET_HTTP: // 获取图片返回
//                    getImg.setImageBitmap((Bitmap) msg.obj);
                    Log.d(TAG, "handleMessage: =====" + msg.obj);
                    break;
            }
        }
    }

    private void getData() {
        new Thread() {
            @Override
            public void run() {
                Message message = Message.obtain(myHandler);
                message.what = Constants.GET_HTTP;
                message.obj = "bitmap";
                message.sendToTarget();
            }
        }.start();
    }


//    private void getData() {
//        new Thread() {
//            @Override
//            public void run() {
//                try {
//                    byte[] image = GetData.getImage(url_img);
//                    Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
//                    Message message = Message.obtain(myHandler);
//                    message.what = Constants.GET_HTTP;
//                    message.obj = bitmap;
//                    message.sendToTarget();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//    }

    private void findView() {
        getImg = (AppCompatImageView) this.findViewById(R.id.img_get);
    }

    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                getData();
                break;
            case R.id.btn_html:
                getHtml();
                break;
            case R.id.btn_post:
                requestCityByPost();
                break;
            case R.id.btn_okhttp:
                new OkHttpThread().start();
                break;
        }
    }

    private void requestCityByPost() {
        new Thread() {
            @Override
            public void run() {
                String city = PostUtils.RequestCityByPost(url_city
                        , "region", "510000", "2");
                Log.d(TAG, "run: ======" + city);
            }
        }.start();

    }

    private void getHtml() {
        new Thread() {
            @Override
            public void run() {
                try {
                    String html = GetData.getHtml(url_html);
                    Log.d(TAG, "run: =========" + html);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    //    private String url_city = "http://192.168.1.102:8080/chicken/wx/public/city.php";
    private class OkHttpThread extends Thread {
        @Override
        public void run() {
            super.run();
            OkHttpUtils.post()
                    .url(url_city)
                    .addParams("mode", "city")
                    .addParams("lvl", "1")
                    .addParams("pid", "")
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Log.d(TAG, "onResponse: ======" + response);
                        }
                    });
        }
    }
}