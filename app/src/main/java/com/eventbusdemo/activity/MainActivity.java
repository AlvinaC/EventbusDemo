package com.eventbusdemo.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.TableLayout;
import android.widget.TextView;

import com.eventbusdemo.R;
import com.eventbusdemo.common.BaseActivity;
import com.eventbusdemo.common.ViewPagerAdapter;
import com.eventbusdemo.fragments.TabFragment;
import com.eventbusdemo.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tabs)
    TabLayout tablayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolbar(Constants.TOOLBAR_TITLE);
        setUpTabs();
        setupViewPager();
        setListener();
    }

    private void setListener() {
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
    }

    private void setUpTabs() {
        tablayout.addTab(tablayout.newTab().setText("Tab 1"));
        tablayout.addTab(tablayout.newTab().setText("Tab 2"));
    }

    private void setupViewPager() {
        final ViewPagerAdapter adapter = new ViewPagerAdapter
                (getSupportFragmentManager(), tablayout.getTabCount());
        viewPager.setAdapter(adapter);
    }
}
