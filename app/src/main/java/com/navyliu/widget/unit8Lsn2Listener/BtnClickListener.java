package com.navyliu.widget.unit8Lsn2Listener;

import android.util.Log;
import android.view.View;

public class BtnClickListener implements View.OnClickListener {
    private final String TAG = this.getClass().getName();

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
        Log.d(TAG, "onClick: ======================");
    }
}
