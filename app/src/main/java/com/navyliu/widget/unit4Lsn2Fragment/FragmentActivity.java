package com.navyliu.widget.unit4Lsn2Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.navyliu.widget.R;

public class FragmentActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        int peachs = 1; // 第十天还剩一个桃子
        for (int i = 0; i < 10; i++) {
            peachs = peachNum(peachs);
        }
        Log.d(TAG, "第一天猴子摘了个桃子: ======" + peachs);
    }




    public int peachNum(int num) {
        return (num + 1) * 2;
    }


    public void clickBtn(View view) {
        switch (view.getId()) {
            case R.id.btn_activity_a: // 跳转到第一个Fragment所在的宿主Activity
                Intent aIntent = new Intent(this, AActivity.class);
                startActivity(aIntent);
                break;
            case R.id.btn_activity_b: // 跳转到动态加载fragment的宿主Activity
                Intent bIntent = new Intent(this, BActivity.class);
                startActivity(bIntent);
                break;
        }
    }



}