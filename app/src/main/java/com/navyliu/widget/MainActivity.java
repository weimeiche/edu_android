package com.navyliu.widget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.navyliu.widget.activity.activity;
import com.navyliu.widget.grid.GridActivity;
import com.navyliu.widget.list.RecyclerViewActivity;
import com.navyliu.widget.unit4Lsn2Fragment.FragmentActivity;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();
    // 定义控件
    private AppCompatTextView textView;
    private TextView htmlTxt;
    private TextView autoTxt;
    private TextView drawableTxt;
    private TextView shenlueTxt;
    private TextView spanTxt;
    private EditText edit;
    private Button btn;

    private CustomTitleBar titleBar;
    private Button leftTitleBtn;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleBar = (CustomTitleBar) findViewById(R.id.title_bar);
        leftTitleBtn = titleBar.getTitleBarLeftBtn();
        leftTitleBtn.setText("123");
        leftTitleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

//        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
//        int bottomToTop = layoutParams.bottomToTop;
//        Button button = new Button(this);
//        layoutParams.leftToRight = leftTitleBtn.getId();


//        constraintLayout = (ConstraintLayout)this.findViewById(R.id.constraint);
//        View view = LayoutInflater.from(this).inflate(R.layout.view_header, null);
//        constraintLayout.addView(view);


//        textView = (AppCompatTextView) this.findViewById(R.id.textview);
//        htmlTxt = (TextView) this.findViewById(R.id.txt_html);
//        autoTxt = (TextView) this.findViewById(R.id.txt_auto);
//        drawableTxt = (TextView) this.findViewById(R.id.txt_drawable);
//        shenlueTxt = (TextView) this.findViewById(R.id.txt_shenglue);
        spanTxt = (TextView) this.findViewById(R.id.txt_span);
        edit = (EditText) this.findViewById(R.id.edit1);
        edit.requestFocus();
        btn = (Button) this.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn:
                        Log.d(TAG, "onClick: =====btn");
                        break;
                }
            }
        });


        String str = "背景色背景色前景色字体颜色删除线15086646341";
        str += "点击事件";
        SpannableString span = new SpannableString(str);
        span.setSpan(new BackgroundColorSpan(Color.RED)
                , 2, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.blue))
                , 6, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new StrikethroughSpan()
                , 13, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new URLSpan("tel:15086646341")
                , 16, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(MainActivity.this, R.string.app_name, Toast.LENGTH_LONG).show();
            }
        }, 27, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        spanTxt.setClickable(true);
        spanTxt.setMovementMethod(LinkMovementMethod.getInstance());
        spanTxt.setText(span);


//        // textview实现走马灯效果
//        shenlueTxt.setSelected(true);
//
//        // textview显示drawable资源图片
//        Drawable drawable_left = getResources().getDrawable(R.drawable.btn_textview);
//        drawable_left.setBounds(0, 0, drawable_left.getIntrinsicWidth(), drawable_left.getIntrinsicHeight());
//        drawableTxt.setCompoundDrawables(drawable_left, drawable_left, drawable_left, drawable_left);
//        drawableTxt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "----", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        // textview自动拨打电话
//        autoTxt.setText("15086646341");
//        // textview显示阴影
//        textView.setText("<斗罗大陆>");
//
//        // textview设置编译显示简单的html便签
//        String str = "内<font color=red>事</font><small>问</small><big>百度</big><br><i>外事</i><b>问</b>谷歌<a href='http://www.baidu.com/'>百度一下</a><img src='emoji_9' />";
//        htmlTxt.setClickable(true);
//        htmlTxt.setMovementMethod(LinkMovementMethod.getInstance());
//
//        //textview中设置显示html中的img资源图片
//        htmlTxt.setText(Html.fromHtml(str, new Html.ImageGetter() {
//            @Override
//            public Drawable getDrawable(String source) {
//                Drawable drawable = null;
//                try {
//                    Field field = R.mipmap.class.getField(source);
//                    int id = field.getInt(null);
//                    drawable = getResources().getDrawable(id);
//                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//                } catch (NoSuchFieldException e) {
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//                return drawable;
//            }
//        }, null));
    }


    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btn_iamgeview:
                Intent intent = new Intent(this, ImageViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_radio:
                Intent radioIntent = new Intent(this, RadioButtonActivity.class);
                startActivity(radioIntent);
                break;
            case R.id.btn_switch:
                Intent switchIntent = new Intent(this, SwitchActivity.class);
                startActivity(switchIntent);
                break;
            case R.id.btn_seekbar:
                Intent seekBarIntent = new Intent(this, SeekBarActivity.class);
                startActivity(seekBarIntent);
                break;
            case R.id.btn_progress:
                Intent progressIntent = new Intent(this, ProgressBarActivity.class);
                startActivity(progressIntent);
                break;
            case R.id.btn_rating:
                Intent ratingIntent = new Intent(this, RatingBarActivity.class);
                startActivity(ratingIntent);
                break;
            case R.id.btn_grideview:
                Intent gridIntent = new Intent(this, GridActivity.class);
                startActivity(gridIntent);
                break;
            case R.id.btn_recycler:
                Intent recyclerIntent = new Intent(this, RecyclerViewActivity.class);
                startActivity(recyclerIntent);
                break;
            case R.id.btn_activity: // 跳转到第4章第一节课Activity
                Intent activityIntent = new Intent(this, activity.class);
                startActivity(activityIntent);
                break;
            case R.id.btn_fragment: // 跳转到第4章第二节课Fragment
                Intent fragmentIntent = new Intent(this, FragmentActivity.class);
                startActivity(fragmentIntent);
                break;
        }
    }

}
