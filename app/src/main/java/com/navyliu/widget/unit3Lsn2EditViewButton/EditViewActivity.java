package com.navyliu.widget.unit3Lsn2EditViewButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import com.navyliu.widget.view.CustomTitleBar;
import com.navyliu.widget.R;
import com.navyliu.widget.test.TestActivity;

public class EditViewActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();
    private TextView spanTxt;
    private EditText edit;
    private Button btn;

    private CustomTitleBar titleBar;
    private Button leftTitleBtn;
    private Button rightTitleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_view);

        titleBar = (CustomTitleBar) findViewById(R.id.title_bar);
        rightTitleBtn = titleBar.getTitleBarRightBtn();
        rightTitleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditViewActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });

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


        String str = "????????????????????????????????????????????????15086646341";
        str += "????????????";
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
                Toast.makeText(EditViewActivity.this, R.string.app_name, Toast.LENGTH_LONG).show();
            }
        }, 27, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        spanTxt.setClickable(true);
        spanTxt.setMovementMethod(LinkMovementMethod.getInstance());
        spanTxt.setText(span);
    }
}