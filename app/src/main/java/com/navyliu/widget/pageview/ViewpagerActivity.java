package com.navyliu.widget.pageview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.navyliu.widget.R;

import java.util.ArrayList;
import java.util.List;

public class ViewpagerActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragments;
    private ViewPagerAdapter pagerAdapter;
    private String[] page_titles = {"第一个选项卡", "第二个选项卡", "第三个选项卡"};
    private String[] page_val = {"first", "second", "third"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pageview);

        init();
        findView();

        pagerAdapter = new ViewPagerAdapter(fragments, this);
        viewPager.setAdapter(pagerAdapter);
    }

    private void init() {
        fragments = new ArrayList<>();
        for (int i = 0; i < page_titles.length; i++) {
            viewpageFragment fragment = com.navyliu.widget.pageview.viewpageFragment.newInstance(page_titles[i], page_val[i]);
            fragments.add(fragment);
        }
    }

    private void findView() {
        viewPager = (ViewPager2) this.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) this.findViewById(R.id.tablayout);
    }


}