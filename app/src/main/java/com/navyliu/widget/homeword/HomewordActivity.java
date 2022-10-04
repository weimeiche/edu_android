package com.navyliu.widget.homeword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.navyliu.widget.R;

public class HomewordActivity extends AppCompatActivity {

    private RadioButton rbtn1, rbtn2, rbtn3, rbtn4;
    private TextView radius1Txt, radius2Txt, radius3Txt, radius4Txt;

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

        findView();
        initFragment();
        initAFragmentListener(); // 初始化AFragment的监听
        chooseFragment(curr_index);
    }

    /**
     * 初始化页面中的控件
     */
    private void findView() {
        rbtn1 = (RadioButton) this.findViewById(R.id.rbtn1);
        rbtn2 = (RadioButton) this.findViewById(R.id.rbtn2);
        rbtn3 = (RadioButton) this.findViewById(R.id.rbtn3);
        rbtn4 = (RadioButton) this.findViewById(R.id.rbtn4);
        radius1Txt = (TextView) this.findViewById(R.id.txt_radius1);
        radius2Txt = (TextView) this.findViewById(R.id.txt_radius2);
        radius3Txt = (TextView) this.findViewById(R.id.txt_radius3);
        radius4Txt = (TextView) this.findViewById(R.id.txt_radius4);
    }

    /**
     * 初始化底部导航对应的fragment，并添加fragment
     */
    private void initFragment() {
        aFragment = new AFragment().newInstance(null);
        bFragment = new BFragment().newInstance(null);
        cFragment = new CFragment().newInstance(null);
        dFragment = new DFragment().newInstance(null);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.layout_frame, dFragment, dFragment.getClass().getName());
        transaction.add(R.id.layout_frame, cFragment, cFragment.getClass().getName());
        transaction.add(R.id.layout_frame, bFragment, bFragment.getClass().getName());
        transaction.add(R.id.layout_frame, aFragment, aFragment.getClass().getName());

        transaction.commitAllowingStateLoss();
    }

    /**
     * 选择第几个选项卡，对应显示第几个fragment
     *
     * @param index
     */
    public void chooseFragment(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Log.d(TAG, "chooseFragment: ======" + index);
        transaction.hide(aFragment);
        transaction.hide(bFragment);
        transaction.hide(cFragment);
        transaction.hide(dFragment);
        rbtn1.setChecked(false);
        rbtn2.setChecked(false);
        rbtn3.setChecked(false);
        rbtn4.setChecked(false);
        switch (index) {
            case 1:
                transaction.show(bFragment);
                rbtn2.setChecked(true);
                break;
            case 2:
                transaction.show(cFragment);
                rbtn3.setChecked(true);
                break;
            case 3:
                transaction.show(dFragment);
                rbtn4.setChecked(true);
                break;
            default:
                transaction.show(aFragment);
                rbtn1.setChecked(true);
                break;
        }
        Log.d(TAG, "chooseFragment: =====rbtn1" + (rbtn1.isChecked() ? "true" : "false"));
        Log.d(TAG, "chooseFragment: =====rbtn2" + (rbtn2.isChecked() ? "true" : "false"));
        Log.d(TAG, "chooseFragment: =====rbtn3" + (rbtn3.isChecked() ? "true" : "false"));
        Log.d(TAG, "chooseFragment: =====rbtn4" + (rbtn4.isChecked() ? "true" : "false"));
        curr_index = index;
        transaction.commitAllowingStateLoss();
    }


    /**
     * 为底部导航栏按钮绑定事件
     *
     * @param view
     */
    public void onclick(View view) {
        int tab = 0;
        switch (view.getId()) {
            case R.id.rbtn2: //
                tab = 1;
                break;
            case R.id.rbtn3:
                tab = 2;
                break;
            case R.id.rbtn4:
                tab = 3;
                break;
            default:
                ;
        }
        Log.d(TAG, "onclick: ======tab" + tab);
        if (tab == curr_index) return;
        chooseFragment(tab);
    }


    /**
     * 初始化AFragment的监听事件
     */
    private void initAFragmentListener() {

        aFragment.setFragmentListener(new AFragment.FragmentListener() {
            @Override
            public void ActivityGetResultFromFragment(Bundle bundle) {
                int tab_index = bundle.getInt("tab_index", 0);
                int new_num = bundle.getInt("new_num", 0);

                switch (tab_index) {
                    case 0: // 显示第一个fragment
                        if (new_num > 99) {
                            radius1Txt.setVisibility(View.VISIBLE); // 显示红点
                            radius1Txt.setText("99+");// 给红点赋值ActivityGetResultFromFragment
                        } else if (new_num > 0) {
                            radius1Txt.setVisibility(View.VISIBLE);
                            radius1Txt.setText(new_num + "");
                        } else {
                            radius1Txt.setVisibility(View.GONE); // 隐藏红点
                        }
                        break;
                    case 1:
                        if (new_num > 99) {
                            radius2Txt.setVisibility(View.VISIBLE); // 显示红点
                            radius2Txt.setText("99+");// 给红点赋值
                        } else if (new_num > 0) {
                            radius2Txt.setVisibility(View.VISIBLE);
                            radius2Txt.setText(new_num + "");
                        } else {
                            radius2Txt.setVisibility(View.GONE); // 隐藏红点
                        }
                        break;
                    case 2:
                        if (new_num > 0)
                            radius3Txt.setVisibility(View.VISIBLE);
                        else
                            radius3Txt.setVisibility(View.GONE);
                        break;
                    case 3:
                        if (new_num > 0)
                            radius4Txt.setVisibility(View.VISIBLE);
                        else
                            radius4Txt.setVisibility(View.GONE);
                        break;
                }
            }
        });
    }


}