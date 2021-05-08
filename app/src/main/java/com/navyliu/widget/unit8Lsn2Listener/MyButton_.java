package com.navyliu.widget.unit8Lsn2Listener;

import android.content.Context;

import androidx.appcompat.widget.AppCompatButton;

public class MyButton_ extends AppCompatButton {

    private final String TAG = this.getClass().getName();

    public MyButton_(Context context) {
        super(context);
    }


//    ①在该组件上触发屏幕事件: boolean onTouchEvent(MotionEvent event);
//②在该组件上按下某个按钮时: boolean onKeyDown(int keyCode,KeyEvent event);
//③松开组件上的某个按钮时: boolean onKeyUp(int keyCode,KeyEvent event);
//④长按组件某个按钮时: boolean onKeyLongPress(int keyCode,KeyEvent event);
//⑤键盘快捷键事件发生: boolean onKeyShortcut(int keyCode,KeyEvent event);
//⑥在组件上触发轨迹球屏事件: boolean onTrackballEvent(MotionEvent event);
//*⑦当组件的焦点发生改变,和前面的6个不同,这个方法只能够在View中重写哦！ protected void onFocusChanged(boolean gainFocus, int direction, Rect previously FocusedRect)


//    @Override
//    public boolean onTrackballEvent(MotionEvent event) {
//        Log.d(TAG, "onTrackballEvent: =======================");
//        return super.onTrackballEvent(event);
//    }
//
//    @Override
//    public boolean onKeyShortcut(int keyCode, KeyEvent event) {
//        Log.d(TAG, "onKeyShortcut: =======================");
//        return super.onKeyShortcut(keyCode, event);
//    }
//
//    @Override
//    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
//        Log.d(TAG, "onKeyLongPress: =========================");
//        return super.onKeyLongPress(keyCode, event);
//    }
//
//    @Override
//    public boolean onKeyUp(int keyCode, KeyEvent event) {
//        Log.d(TAG, "onKeyUp: =====================");
//        return super.onKeyUp(keyCode, event);
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        Log.d(TAG, "onKeyDown: ==================");
//        return super.onKeyDown(keyCode, event);
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        Log.d(TAG, "onTouchEvent: ==================");
//        return super.onTouchEvent(event);
//    }
}
