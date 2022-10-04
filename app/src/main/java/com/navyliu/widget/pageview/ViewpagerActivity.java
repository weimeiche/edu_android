package com.navyliu.widget.pageview;

import android.os.Bundle;
import android.view.View;

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

        findView();
        init();

        pagerAdapter = new ViewPagerAdapter(fragments, this);
        viewPager.setAdapter(pagerAdapter);
    }

    private void init() {
        fragments = new ArrayList<>();
        for (int i = 0; i < page_titles.length; i++) {
            viewpageFragment fragment = com.navyliu.widget.pageview.viewpageFragment.newInstance(page_titles[i], page_val[i]);
            fragments.add(fragment);
            tabLayout.addTab(tabLayout.newTab().setText(page_titles[i]));
        }

        // 绑定选项卡和viewpager的联动关系
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // 绑定viewpager和选项卡的联动关系
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.setScrollPosition(position, 0, false);
            }
        });
    }

    private void findView() {
        viewPager = (ViewPager2) this.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) this.findViewById(R.id.tablayout);
    }


}