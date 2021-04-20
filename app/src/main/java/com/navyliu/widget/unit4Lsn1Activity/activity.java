package com.navyliu.widget.unit4Lsn1Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.navyliu.widget.constants.Constants;
import com.navyliu.widget.R;

public class activity extends AppCompatActivity {

    private String TAG = this.getClass().getName();

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);

        btn = (Button) this.findViewById(R.id.btn_seconed);

        AppManager.getAppManager().addActivity(this);
        Log.d(TAG, "onCreate: ====");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ========");
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
        AppManager.getAppManager().printAllActivity();
        AppManager.getAppManager().finishActivity(this);
    }

    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btn_seconed:
                startActivity();
                break;
        }
    }

    private void startActivity() {
//        // 常用的跳转方式
//        Intent intent = new Intent(this, SeconedActivity.class);
//        startActivity(intent);

//        // 单个参数传递
//        Intent intent = new Intent(this, SeconedActivity.class);
//        intent.putExtra("param", "this param from activity");
//        startActivity(intent);

//        // 多个参数传递
//        Intent intent = new Intent(this, SeconedActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putInt("age", 3);
//        bundle.putString("name","navyliu");
//        intent.putExtras(bundle);
//        startActivity(intent);

        // 需要接收即将跳转的Activity返回的参数
        Intent intent = new Intent(this, SeconedActivity.class);
        startActivityForResult(intent, Constants.RESULT_OK);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d(TAG, "onActivityResult: =========");
        Log.d(TAG, "onActivityResult: ==========requestCode:" + requestCode + ",resultCode:" + resultCode);
        // 这里可以根据请求code，或者返回code进行分支
        // 请求code，requestCode，就是在跳转的时候本页面传递的参数，即是startActivityForResult方法中的参数
        // 返回code，resultCode，就是在第二个activity中传递回来的参数，即是setResult(Constants.RESULT_BACK, intent);方法中的参数
        switch (resultCode) {
            case Constants.RESULT_BACK:
                Bundle bundle = data.getExtras();
                int age = bundle.getInt("age");
                String nickname = bundle.getString("nickname");
                Log.d(TAG, "onActivityResult: ======第二个activity传回的参数为：昵称：" + nickname + ",年龄:" + age);
                break;
        }
        // 特别注意：super方法一定要放到onActivityResult方法的最后
        super.onActivityResult(requestCode, resultCode, data);
    }

}