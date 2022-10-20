package com.navyliu.widget.unit3Lsn3ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import com.navyliu.widget.R;

public class SeekBarActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();
    private SeekBar seekBar;
    private AppCompatTextView resultTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar);

        seekBar = (SeekBar) this.findViewById(R.id.sb);
        resultTxt = (AppCompatTextView) this.findViewById(R.id.txt_result);

//        seekBar.setMin();
//        seekBar.setMax();

        /**
         * 给SeekBar控件绑定监听 步骤：
         * 1、通过findViewId 获取控件
         * 2、通过setOnSeekBarChangeListener 去绑定监听
         */
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /**
             * 滑动条的进度被改变的时候触发该方法
             * @param seekBar 进度被改变的这个进度条
             * @param progress 当前进度，即改变之后进度
             * @param fromUser
             */
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "onProgressChanged: ======" + progress);
                // 要小数点的话，可以先将max放大以后在缩小
                resultTxt.setText("当前进度为：" + progress + "%");
            }

            /**
             * 开始接触SeekBar的时候回调， 手指按下的时候触发
             * @param seekBar
             */
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            /**
             * 结束接触SeekBar的时候回调， 手指松开的时候触发
             * @param seekBar
             */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}