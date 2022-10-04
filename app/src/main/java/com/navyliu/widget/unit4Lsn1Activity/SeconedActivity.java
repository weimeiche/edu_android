package com.navyliu.widget.unit4Lsn1Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.navyliu.widget.constants.Constants;
import com.navyliu.widget.R;

public class SeconedActivity extends AppCompatActivity {

    private String TAG = this.getClass().getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconed);

//        // 单个参数接收
//        String param = this.getIntent().getStringExtra("param");
//        Log.d(TAG, "onCreate: =====接收的参数为："+param);


//        // 多个参数接收
//        Bundle bundle = this.getIntent().getExtras();
//        int age = bundle.getInt("age");
//        String nameStr = bundle.getString("name");
//        Log.d(TAG, "onCreate: =====接收的bundle参数为：年龄：" + age + ", 名字:" + nameStr);


        Log.d(TAG, "onCreate: =======");
        AppManager.getAppManager().addActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: =======");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ========");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ===========");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ==========");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ========");
    }

    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btn_finish: // 关闭当前activity
                finish();
                break;
            case R.id.btn_finish_app: // 关闭当前app应用
                finishApp(this);
                break;
            case R.id.btn_finish_params: // 关闭当前activity，同时返回参数
                finishActivityParams();
                break;
        }
    }

    private void finishActivityParams() {
        Log.d(TAG, "finishActivityParams: =====");
        Intent intent = getIntent(); // 初始化intent
        Bundle bundle = new Bundle(); // 初始化bundle
        // 给bundle绑定值
        bundle.putInt("age", 4);
        bundle.putString("nickname", "午夜垂钓者");
        // 将bundle绑定到intent上
        intent.putExtras(bundle);
        // 设置返回的参数和intent
        setResult(Constants.RESULT_BACK, intent);
        // 关闭当前页
        finish();
    }

    private void finishApp(Context context) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        setResult(12, intent);
        finish();
    }
}