package com.navyliu.widget.unit3Lsn3ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;

import android.os.Bundle;
import android.widget.RatingBar;

import com.navyliu.widget.R;

public class RatingBarActivity extends AppCompatActivity {

private AppCompatRatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar);

    }
}