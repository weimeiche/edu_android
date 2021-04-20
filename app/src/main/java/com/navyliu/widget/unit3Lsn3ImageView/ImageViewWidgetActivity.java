package com.navyliu.widget.unit3Lsn3ImageView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.navyliu.widget.R;

public class ImageViewWidgetActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_widget);
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
        }
    }

}
