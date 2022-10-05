package com.navyliu.widget.unit3Lsn1Textview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.navyliu.widget.MainActivity;
import com.navyliu.widget.R;

import java.lang.reflect.Field;

public class TextviewActivity extends AppCompatActivity {

    // 定义控件
    private AppCompatTextView textView;
    private TextView htmlTxt;
    private TextView autoTxt;
    private TextView drawableTxt;
    private TextView shenlueTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textview);

        textView = (AppCompatTextView) this.findViewById(R.id.textview);
        htmlTxt = (TextView) this.findViewById(R.id.txt_html);
        autoTxt = (TextView) this.findViewById(R.id.txt_auto);
        drawableTxt = (TextView) this.findViewById(R.id.txt_drawable);
        shenlueTxt = (TextView) this.findViewById(R.id.txt_shenglue);

        // textview实现走马灯效果
        shenlueTxt.setSelected(true);

        // textview显示drawable资源图片
        Drawable drawable_left = getResources().getDrawable(R.drawable.btn_textview);
        drawable_left.setBounds(0, 0, drawable_left.getIntrinsicWidth(), drawable_left.getIntrinsicHeight());
        drawableTxt.setCompoundDrawables(drawable_left, drawable_left, drawable_left, drawable_left);
        drawableTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TextviewActivity.this, "----", Toast.LENGTH_SHORT).show();
            }
        });

        // textview自动拨打电话
        autoTxt.setText("15086646341");
        // textview显示阴影
        textView.setText("<斗罗大陆>");

        // textview设置编译显示简单的html便签
        String str = "内<font color=red>事</font><small>问</small><big>百度</big><br><i>外事</i><b>问</b>谷歌<a href='http://www.baidu.com/'>百度一下</a><img src='emoji_9' />";
        htmlTxt.setText(Html.fromHtml(str));
        htmlTxt.setClickable(true);
        htmlTxt.setMovementMethod(LinkMovementMethod.getInstance());

        //textview中设置显示html中的img资源图片
        htmlTxt.setText(Html.fromHtml(str, new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                Drawable drawable = null;
                try {
                    Field field = R.mipmap.class.getField(source);
                    int id = field.getInt(null);
                    drawable = getResources().getDrawable(id);
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                return drawable;
            }
        }, null));
    }
}
