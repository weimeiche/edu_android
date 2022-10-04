package com.navyliu.widget.unit3Lsn3ImageView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.navyliu.widget.R;

public class RadioButtonActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {


    private final String TAG = this.getClass().getName();
    private RadioGroup jueseRG;
    private RadioButton aaRadio;
    private CheckBox shashengwanCheck;
    private Button checkBtn;

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
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton btn = RadioButtonActivity.this.findViewById(checkedId);
                String str = btn.getText().toString();
                Toast.makeText(RadioButtonActivity.this,"选中了"+str,Toast.LENGTH_LONG).show();
            }
        });


        shashengwanCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.getId() == R.id.check_shashengwan) {
                    Log.d(TAG, "onCheckedChanged: ====" + isChecked);
                }
            }
        });

        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void findViewId() {
        jueseRG = (RadioGroup) this.findViewById(R.id.rg_juese);
        shashengwanCheck = (CheckBox) this.findViewById(R.id.check_shashengwan);
        checkBtn = (Button)this.findViewById(R.id.btn_check);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.check_eshao:
                break;
        }
    }
}