package com.navyliu.widget.unit8Lsn2Listener;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;

import com.navyliu.widget.R;

public class ListenerActivity extends AppCompatActivity
        implements View.OnClickListener, View.OnTouchListener {

    private final String TAG = this.getClass().getName();
    private AppCompatImageView imageview;
    private AppCompatImageView mutliImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listener);

        findview();
        /**
         * 绑定监听的五种方式
         */
        setListener(); //  直接用匿名内部类
        implementListener(); // 内部类绑定监听
        implementOutListener(); // 使用外部类
        bindSelfListener(); // 使用Activity自己的事件监听

        setTextWather();
        setTouchListener(); // 绑定触摸监听

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
     * 绑定触摸监听  开始
     * onTouch(View v, MotionEvent event):这里面的参数依次是触发触摸事件的组件
     * ,触碰事件event 封装了触发事件的详细信息，同样包括事件的类型、触发时间等信息。
     * 比如event.getX(),event.getY()
     * event.getDownTime()
     * event.getEventTime()
     * 我们也可以对触摸的动作类型进行判断,使用event.getAction( )再进行判断;如:
     * event.getAction == MotionEvent.ACTION_DOWN：按下事件
     * event.getAction == MotionEvent.ACTION_MOVE:移动事件
     * event.getAction == MotionEvent.ACTION_UP:弹起事件
     */
    float startX = 0, startY = 0;
    float getX = 0, getY = 0;

    private void setTouchListener() {
        imageview.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // 按下事件
                        Log.d(TAG, "onTouch: ======按下去了====");
                        Log.d(TAG, "onTouch: ======event.getX()=====" + event.getX() + "====event.getY()=====" + event.getY());
                        startX = imageview.getX();
                        startY = imageview.getY();
                        getX = event.getX();
                        getY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP: // 弹起事件
                        Log.d(TAG, "onTouch: =====弹起来了====");
                        Log.d(TAG, "onTouch: ======event.getX()=====" + event.getX() + "====event.getY()=====" + event.getY());
                        break;
                    case MotionEvent.ACTION_MOVE: // 移动事件
                        Log.d(TAG, "onTouch: =======动了动了======");
                        imageview.setX(startX + (event.getX() - getX));
                        imageview.setY(startY + (event.getY() - getY));
                        imageview.invalidate();
                        break;
                }
                return true;
            }
        });
    }

    /**
     * 绑定触摸监听  结束
     */

    private void findview() {
        imageview = (AppCompatImageView) this.findViewById(R.id.imageview);
        mutliImg = (AppCompatImageView) this.findViewById(R.id.img_mutli);
        mutliImg.setOnTouchListener(this);
    }

    /**
     * 多点触控监听  开始
     *
     * @param v
     * @param event
     * @return
     */
    // 缩放控制
    private Matrix matrix = new Matrix();
    private Matrix savedMatrix = new Matrix();
    // 不同状态标识
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;
    // 定义第一个按下的点，两个接触点的重点，以及初始的两点之间的距离
    private PointF startPoint = new PointF();
    private PointF midPoint = new PointF();
    private float oriDis = 1f;


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        AppCompatImageView img = (AppCompatImageView) v;
        // event.getAction() & MotionEvent.ACTION_MASK
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: // 单个手指
                Log.d(TAG, "onTouch: ===MotionEvent.ACTION_DOWN=====");
                matrix.set(img.getMatrix());
                savedMatrix.set(matrix);
                startPoint.set(event.getX(), event.getY());
                mode = DRAG;
                break;
            case MotionEvent.ACTION_POINTER_DOWN: // 双指
                Log.d(TAG, "onTouch: ========MotionEvent.ACTION_POINTER_DOWN==========");
                oriDis = distance(event);
                if (oriDis > 10f) {
                    savedMatrix.set(matrix);
                    midPoint = middle(event);
                    mode = ZOOM;
                }
                break;
            case MotionEvent.ACTION_UP: // 放开手指
            case MotionEvent.ACTION_POINTER_UP:
                Log.d(TAG, "onTouch: ========MotionEvent.ACTION_UP==========");
                mode = NONE;
                break;
            case MotionEvent.ACTION_MOVE: // 单指滑动事件
                Log.d(TAG, "onTouch: ========MotionEvent.ACTION_MOVE==========");
                if (mode == DRAG) {
                    // 一根手指拖动
                    matrix.set(savedMatrix);
                    matrix.postTranslate(event.getX() - startPoint.x, event.getY() - startPoint.y);
                } else if (mode == ZOOM) {
                    // 两根手指滑动
                    float newDist = distance(event);
                    if (newDist > 10f) {
                        matrix.set(savedMatrix);
                        float scale = newDist / oriDis;
                        matrix.postScale(scale, scale, midPoint.x, midPoint.y);
                    }
                }
                break;
        }
        // 设置Imageview的Matrix
        img.setImageMatrix(matrix);
        return true;
    }

    // 计算两点之间的距离
    private float distance(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    // 计算两个触摸点的中点
    private PointF middle(MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        return new PointF(x / 2, y / 2);
    }
    /**
     * 多点触控监听  结束
     */


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
         * @param s     输入框里面的内容，可以通过tostring 转成一个字符串
         * @param start 这次（即将）输入的放到开始位置
         * @param count 这次（即将）输入的内容所要替换的字符串长度
         * @param after 这次（即将）输入的字符串长度
         */
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // 输入之前调用
            Log.d(TAG, "beforeTextChanged: ====" + s.toString() + "==start："
                    + start + "===count：" + count + "===after：" + after);
        }

        /**
         * @param s      输入框里面的内容，可以通过tostring 转成一个字符串
         * @param start  这次输入的放到开始位置
         * @param before 这次输入的内容所要替换的字符串长度
         * @param count  这次输入字符串长度
         */
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // 输入以后调用
            Log.d(TAG, "onTextChanged: ====" + s.toString() + "==start："
                    + start + "===count：" + count + "===before：" + before);
        }

        /**
         * @param s 当前输入框输入内容以后所有参数，可以用tostring来拿输入框里面内容
         */
        @Override
        public void afterTextChanged(Editable s) {
            // 文本改变以后调用
            Log.d(TAG, "afterTextChanged: ====" + s.toString());
        }
    }

    private void setTextWather() {
        AppCompatEditText editText = (AppCompatEditText) this.findViewById(R.id.edit);
        editText.addTextChangedListener(new MyTextWatcher());
    }


}