package com.navyliu.widget.unit3Lsn3ImageView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.navyliu.widget.R;

public class ProgressBarActivity extends AppCompatActivity {

    private ProgressBar normalPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        normalPB = (ProgressBar) this.findViewById(R.id.pb_normal);

    }


    /**
     * is have has  返回的一般是Boolean类型
     * @param view
     */

    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btn_max:
                normalPB.setMax(1000);
                break;
            case R.id.btn_get_max:
                int max = normalPB.getMax();
                int progress = normalPB.getProgress();
                int secondaryProgress = normalPB.getSecondaryProgress();
                Toast.makeText(this, "进度条的最大值为：" + max, Toast.LENGTH_LONG).show();
                break;
        }
    }
}