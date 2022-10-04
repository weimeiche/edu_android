package com.navyliu.widget.unit8Lsn2Listener;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

import androidx.appcompat.widget.AppCompatEditText;

import com.navyliu.widget.R;

public class CustomClearEditText extends AppCompatEditText
        implements View.OnFocusChangeListener, TextWatcher {

    // 删除按钮的引用
    private Drawable mClearDrawable;
    // 控件是否有焦点
    private boolean hasFoucs;

    public CustomClearEditText(Context context) {
        this(context, null);
    }

    public CustomClearEditText(Context context, AttributeSet attr) {
        // 这里构造方法也很重要，不加这个很多属性不能再xml中定义
        this(context, attr, android.R.attr.editTextStyle);
    }

    public CustomClearEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        // 获取edittext的drawableright，如果没有设置，则显示默认的图片
        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable == null) {
            // throw new nullPointerException("you can add drawableright attribute in xml");
            mClearDrawable = getResources().getDrawable(R.drawable.btn_clear);
        }
        mClearDrawable.setBounds(0, 0
                , mClearDrawable.getIntrinsicWidth()
                , mClearDrawable.getIntrinsicHeight());
        // 默认隐藏图标
        setClearIconVisible(false);
        // 设置焦点改变的监听
        setOnFocusChangeListener(this);
        // 设置输入框里面内容发生改变的监听
        addTextChangedListener(this);
    }

    /***
     * 因为我们不能直接给edittext设置点击事件，所以我们用记住我们按下的位置来模拟点击事件
     * 当我们按下的位置 在 edittext的宽度 - 图标到控件右边的间距 和
     * edittext的宽度 - 图标到控件右边间距之间我们算点击了图标，竖直方向就没有考虑
     */
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {

                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight()) && (event.getX() < (getWidth() - getPaddingRight()));
                if (touchable) {
                    this.setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }


    /**
     * TODO<设置清楚图标的显示和隐藏，调用setCompoundDrawables为edittext绘制上去>
     *
     * @param visible
     * @return void
     */
    protected void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0]
                , getCompoundDrawables()[1]
                , right
                , getCompoundDrawables()[3]);
    }

    @Override
    public void afterTextChanged(Editable s) {
        // TODO Auto-generated method stub

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // TODO Auto-generated method stub
    }

    /**
     * 当输入框里面的内容发生变化的时候回调的方法
     */
    public void onTextChanged(CharSequence s, int start, int count, int after) {
        if (hasFoucs) {
            setClearIconVisible(s.length() > 0);
        }
    }

    /**
     * 当customclearedittext焦点发生变化的时候，判断里面字符串长度设置清楚图标的显示与隐藏
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        // TODO Auto-generated method stub
        this.hasFoucs = hasFocus;
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }
    }

    // 设置被晃动的动画
    public void setShakeAnimation() {
        this.setAnimation(shakeAnimation(5));
    }


    /**
     * TODO<晃动动画>
     *
     * @param counts 1s晃动多少下
     * @return Animation
     * @throw
     */
    public static Animation shakeAnimation(int counts) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }


}
