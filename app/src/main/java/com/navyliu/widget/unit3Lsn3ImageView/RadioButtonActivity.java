package com.navyliu.widget.unit3Lsn3ImageView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.navyliu.widget.R;

import java.util.ArrayList;

public class RadioButtonActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {


    private final String TAG = this.getClass().getName();
    private RadioGroup jueseRG;
    private RadioButton aaRadio;
    private CheckBox eshaoChk, shashengwanChk, xiaowuChk, quanyechaChk;
    private Button checkBtn;
    private TextView checkTxt;

    private ArrayList<String> douluoArr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button);

        findViewId();
        setListener();
    }

    private void setListener() {

        jueseRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton rbtn = (RadioButton) RadioButtonActivity.this.findViewById(checkedId);
                String str = rbtn.getText().toString();

                Toast.makeText(RadioButtonActivity.this, "当前选中的是：" + str, Toast.LENGTH_LONG).show();
                Log.d(TAG, "onCheckedChanged: =========radioGroup.getChildCount())======" + radioGroup.getChildCount());
                Log.d(TAG, "onCheckedChanged: ==================R.id.raido_xiaosan=========" + R.id.raido_xiaosan);
                RadioButton btn = (RadioButton) radioGroup.getChildAt(1); // 索引号
                Log.d(TAG, "onCheckedChanged: ===================radioGroup.getChildAt(1).getId()========" + btn.getId());

                if (btn.isChecked()) {
                    Log.d(TAG, "onCheckedChanged: =================当前选中了唐三对应的单选按钮==============");
                } else {
                    Log.d(TAG, "onCheckedChanged: =================当前没有选中了唐三对应的单选按钮==============");
                }
            }
        });


        /**
         * 绑定监听步骤：
         * 1、通过findViewById获取xml布局中的控件；
         * 2、在本类（一般是Activity、Fragment）中继承CompoundButton.OnCheckedChangeListener 接口
         * 3、通过setOnCheckedChangeListener方法 给控件绑定监听，参数为回调的类，触发监听以后会调用回调类里面的 onCheckedChanged 方法
         * 4、在onCheckedChanged 里面去解析，并执行希望执行动作
         */
        eshaoChk.setOnCheckedChangeListener(this);
        shashengwanChk.setOnCheckedChangeListener(this);
        xiaowuChk.setOnCheckedChangeListener(this);
        quanyechaChk.setOnCheckedChangeListener(this);
    }

    private void findViewId() {
        jueseRG = (RadioGroup) this.findViewById(R.id.rg_juese);
        checkBtn = (Button) this.findViewById(R.id.btn_check);
        aaRadio = (RadioButton) this.findViewById(R.id.raido_xiaowu);

        eshaoChk = (CheckBox) this.findViewById(R.id.check_eshao);
        shashengwanChk = (CheckBox) this.findViewById(R.id.check_shashengwan);
        xiaowuChk = (CheckBox) this.findViewById(R.id.check_xiaowu);
        quanyechaChk = (CheckBox) this.findViewById(R.id.check_quyecha);

        checkTxt = (TextView) this.findViewById(R.id.txt_check);
    }

    /**
     * CompoundButton（包括CHeckBox，RadioButton）的回调函数
     * @param compoundButton  是CheckBox的父级，可以强制转换为CheckBox
     * @param isChecked  当前操作的这个CheckBox的选中状态，如果为FALSE，就表示没选中，如果为TRUE，就表示选中
     */
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        CheckBox checkBox = (CheckBox) compoundButton;
        String str = compoundButton.getText().toString();
        if (isChecked) {
            douluoArr.add(str);
        } else {
            douluoArr.remove(str);
        }

        String s = "";
        for (int i = 0; i < douluoArr.size(); i++) {
            String s1 = douluoArr.get(i);
            s += s1 + '，';
        }
        checkTxt.setText(s);
    }

}