package com.navyliu.widget.unit8Lsn3Http;


import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.navyliu.widget.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class HttpClientActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_client);
    }

    public void onclick(View view){
        switch (view.getId()){
            case R.id.btn_get:
//                new OkHttpThread().start();
                break;
            case R.id.btn_post:
                break;
        }
    }

}