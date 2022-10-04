package com.navyliu.widget.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.navyliu.widget.R;
import com.navyliu.widget.constants.Constants;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.net.ssl.HttpsURLConnection;

public class TestActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();
    private int[][] arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        for (int i = 0; i < 50; i++) {
            arr[i][0] = 1; // 第一列的数字为1
        }
        for (int i = 1; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                arr[i][j] = arr[i-1][j-1]+arr[i-1][j];
            }
        }
        // 打印结果
        String str = "第50行数字为：";
        for (int i :   arr[49]) {
            str += ','+i;
        }
        Log.d(TAG, "onCreate: =======");

//        findview();
//        myHandler = new MyHandler();
//        new AsyncTask<>()
    }






    private AppCompatTextView textView;
    private AppCompatImageView imageView;

    private MyHandler myHandler;
    private int count = 0;

    private String img_src = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimage109.360doc.com%2FDownloadImg%2F2018%2F04%2F1713%2F130458778_4_20180417015601550.png&refer=http%3A%2F%2Fimage109.360doc.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1622309637&t=5d877a45291d0f37dba19fc6ac99acb1";

    private void findview() {
        textView = (AppCompatTextView) this.findViewById(R.id.textview);
        imageView = (AppCompatImageView) this.findViewById(R.id.imageview);
    }


    public class MyHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constants.GET_HTTP:
                    Bundle bundle = msg.getData();
                    String mode = bundle.getString("mode");
                    Log.d(TAG, "handleMessage: =========" + mode);
                    showLoadingImg(msg.obj);
                    break;
            }
        }
    }

    private void showLoadingImg(Object obj) {

        Bitmap bitmap;
        int index = count % 4;
        switch (index) {
            case 1:
                bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.loading_2);
                break;
            case 2:
                bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.loading_3);
                break;
            case 3:
                bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.loading_4);
                break;
            default:
                bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.loading_1);
                break;
        }
        imageView.setImageBitmap(bitmap);
        textView.setText(count + "");
    }


    /**
     * 使用异步任务的规则
     * 1、生命一个类继承于AsyncTask， 标注三个参数的类型
     * 2、第一个参数标识要执行的任务，通常是网络路径；
     * 第二个参数标识进度的刻度；
     * 第三个参数标识任务返回的结果；
     */
    public class MyTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            // 任务执行前的操作
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            // 主要是完成耗时操作
            // 使用网络请求类来进行网络数据的请求
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            // 主要是完成UI更新操作
            super.onPostExecute(bitmap);
        }

    }


    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btn:
//                loadingPicture();
                startLoading();
                break;
        }
    }

    private void loadingPicture() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.layout_progress, null);
        AppCompatTextView textView = (AppCompatTextView) view.findViewById(R.id.txt_prompt);
        textView.setText("请稍后");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog progressDialog = builder.create();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }


    private void startLoading() {
        new Thread(runnable).start();
    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            count++;
            try {
                Thread.sleep(5000);
                Log.d(TAG, "run: =========" + count);
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("mode", "asdf");
                message.setData(bundle);
                message.what = Constants.GET_HTTP;
                myHandler.sendMessage(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.removeCallbacks(runnable);
    }
}