package com.navyliu.widget.homeword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.navyliu.widget.R;

public class HomewordActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();
    private AFragment aFragment;
    private BFragment bFragment;
    private CFragment cFragment;
    private DFragment dFragment;
    private int curr_index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeword);

        initFragment();
    }

    /**
     * 添加fragment
     */
    private void initFragment() {
        aFragment = new AFragment().newInstance(null);
        bFragment = new BFragment().newInstance(null);
        cFragment = new CFragment().newInstance(null);
        dFragment = new DFragment().newInstance(null);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.layout_frame, dFragment, aFragment.getClass().getName());
        transaction.add(R.id.layout_frame, cFragment, aFragment.getClass().getName());
        transaction.add(R.id.layout_frame, bFragment, aFragment.getClass().getName());
        transaction.add(R.id.layout_frame, aFragment, aFragment.getClass().getName());

        transaction.commitAllowingStateLoss();
    }

    private void chooseFragment(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Log.d(TAG, "chooseFragment: ======" + index);
        transaction.hide(aFragment);
        transaction.hide(bFragment);
        transaction.hide(cFragment);
        transaction.hide(dFragment);
        switch (index) {
            case 1:
                transaction.show(bFragment);
                break;
            case 2:
                transaction.show(cFragment);
                break;
            case 3:
                transaction.show(dFragment);
                break;
            default:
                transaction.show(aFragment);
                break;
        }
        curr_index = index;
        transaction.commitAllowingStateLoss();
    }


    public void onclick(View view) {
        int tab = 0;
        switch (view.getId()) {
            case R.id.btn2:
                tab = 1;
                break;
            case R.id.btn3:
                tab = 2;
                break;
            case R.id.btn4:
                tab = 3;
                break;
            default:
                ;
        }
        if (tab == curr_index) return;
        chooseFragment(tab);
    }
}