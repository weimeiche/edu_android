package com.navyliu.widget.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.navyliu.widget.R;

public class TestActivity extends AppCompatActivity {

    private RelativeLayout relativeLayout;
    private TextView usernameTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        usernameTxt = (TextView) this.findViewById(R.id.txt_username);

        relativeLayout = (RelativeLayout) this.findViewById(R.id.layout_relative);
        TextView textView = new TextView(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.BELOW, usernameTxt.getId());
        textView.setText("新增的textview控件");
        textView.setTextSize(30);
        textView.setLayoutParams(layoutParams);
        relativeLayout.addView(textView);




    }
}