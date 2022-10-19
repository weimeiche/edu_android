package com.navyliu.widget.unit3Lsn3ImageView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.navyliu.widget.R;

public class SwitchActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();
    private Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);

        aSwitch = (Switch) this.findViewById(R.id.btn_switch);

        /**
         * 为swith开关设置监听
         */
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /**
             * @param compoundButton 当前触发选中状态改变的按钮
             * @param isChecked 当前触发选中状态改变的按钮，改变以后的状态
             */
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                Switch switchBtn = (Switch) compoundButton;
                Log.d(TAG, "onCheckedChanged: =======switchBtn id is :" + switchBtn.getId());
                Log.d(TAG, "onCheckedChanged: ========aSwitch id is : " + aSwitch.getId());
                Log.d(TAG, "onCheckedChanged: =============isChecked:" + isChecked);
            }
        });

    }

    public void onclick(View view) {
        // isChecked 可以获取开关控件的打开状态， 返回True，就打开，否则是关闭
        boolean checked = aSwitch.isChecked();
        checked = !checked;
        // 用setChecked方法设置开关的状态
        aSwitch.setChecked(checked);
    }
}