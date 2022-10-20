package com.navyliu.widget.unit3Lsn3ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;

import com.navyliu.widget.R;

public class RatingBarActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();
    private AppCompatRatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar);

        ratingBar = (AppCompatRatingBar) this.findViewById(R.id.ratingbar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            /**
             * ratingbar被改变以后触发
             * @param ratingBar
             * @param rating
             * @param fromUser
             */
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Log.d(TAG, "onRatingChanged: =====当前选中的星星数量：=" + rating);
//                if (rating == 1)
            }
        });

    }

    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btn_numstar:
                ratingBar.setNumStars(7);
                ratingBar.setRating(4);
                ratingBar.setStepSize(1);
                break;
        }
    }
}