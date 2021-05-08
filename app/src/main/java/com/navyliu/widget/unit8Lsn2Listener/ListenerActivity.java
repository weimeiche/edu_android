package com.navyliu.widget.unit8Lsn2Listener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.navyliu.widget.R;

public class ListenerActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listener);

        /**
         * 绑定监听的五种方式
         */
        setListener(); //  直接用匿名内部类
        implementListener(); // 内部类绑定监听
        implementOutListener(); // 使用外部类
        bindSelfListener(); // 使用Activity自己的事件监听

        setTextWather();

//        AppCompatButton listenerBtn = (AppCompatButton) this.findViewById(R.id.btn_setListener);
//listenerBtn.setOnClickListener(new MyListener());

//        AppCompatButton selfBtn = (AppCompatButton) this.findViewById(R.id.btn_self_click);
//        selfBtn.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return false;
//            }
//        });

    }

    /**
     * 绑定在布局上的监听
     *
     * @param view
     */
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btn_xml_click:
                Log.d(TAG, "onclick: =============btn_xml_click=======");
                break;
        }
    }


    /**
     * 使用Activity自己的事件监听  开始
     * 1、当前activity、fragment等要继承于对应的监听，
     * eg：AppCompatActivity implements View.OnClickListener
     * 2、实现对应的方法，eg：onClick(View v)
     * 3、绑定监听，传入参数为this，eg：selfBtn.setOnClickListener(this);
     */
    private void bindSelfListener() {
        AppCompatButton selfBtn = (AppCompatButton) this.findViewById(R.id.btn_self_click);
        selfBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_self_click:
                Log.d(TAG, "onClick: =====btn_self_click==");
                break;
        }
    }
    /**
     * 使用Activity自己的事件监听  结束
     */


    /**
     * 匿名内部类绑定监听
     */
    private void setListener() {
        AppCompatButton listenerBtn = (AppCompatButton) this.findViewById(R.id.btn_setListener);
        listenerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ========btn_setListener=");
            }
        });

        listenerBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
    }


    /**
     * 通过内部类的方法来绑定监听  开始
     * 步骤：
     * 1、定义一个内部类，内部类继承于相关的监听，
     * eg：View.OnClickListener，View.OnLongClickListener等；
     * 2、通过set方法来绑定内部类的监听，
     * eg：implementBtn.setOnClickListener(new MyListener());
     * implementBtn.setOnLongClickListener(new MyListener());等
     */

    // 定义内部类
    public class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: ======MyListener implements View.OnClickListener=");
        }
    }


    private void implementListener() {
        AppCompatButton implementBtn = (AppCompatButton) this.findViewById(R.id.btn_implement);
        implementBtn.setOnClickListener(new MyListener());
    }

    /**
     * 通过内部类的方法来绑定监听  结束
     */


    /**
     * 外部类来绑定监听
     */
    private void implementOutListener() {
        AppCompatButton outBtn = (AppCompatButton) this.findViewById(R.id.btn_out_implement);
        outBtn.setOnClickListener(new BtnClickListener());
    }


    private class MyTextWatcher implements TextWatcher {

        /**
         *
         * @param s 输入框里面的内容，可以通过tostring 转成一个字符串
         * @param start 这次（即将）输入的放到开始位置
         * @param count 这次（即将）输入的内容所要替换的字符串长度
         * @param after 这次（即将）输入的字符串长度
         */
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // 输入之前调用
            Log.d(TAG, "beforeTextChanged: ===="+s.toString()+"==start："
                    +start+"===count："+count+"===after："+after);
        }

        /**
         *
         * @param s 输入框里面的内容，可以通过tostring 转成一个字符串
         * @param start 这次输入的放到开始位置
         * @param before 这次输入的内容所要替换的字符串长度
         * @param count 这次输入字符串长度
         */
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // 输入以后调用
            Log.d(TAG, "onTextChanged: ===="+s.toString()+"==start："
                    +start+"===count："+count+"===before："+before);
        }

        /**
         * @param s 当前输入框输入内容以后所有参数，可以用tostring来拿输入框里面内容
         */
        @Override
        public void afterTextChanged(Editable s) {
            // 文本改变以后调用
            Log.d(TAG, "afterTextChanged: ===="+s.toString());
        }
    }

    private void setTextWather() {
        AppCompatEditText editText = (AppCompatEditText) this.findViewById(R.id.edit);
        editText.addTextChangedListener(new MyTextWatcher());
    }


}