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
import com.eventbusdemo.models.TempModel;
import com.eventbusdemo.util.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tabs)
    TabLayout tablayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private ArrayList<TempModel> list;
    private ArrayList<TempModel> listFavs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolbar(Constants.TOOLBAR_TITLE);
        setUpList();
        setUpTabs();
        setupViewPager();
        setListener();
    }

    private void setUpList() {
        list = new ArrayList<TempModel>();
        TempModel a = new TempModel();
        a.setName("Arthur Hailey");
        TempModel b = new TempModel();
        b.setName("Robert Ludlum");
        TempModel c = new TempModel();
        c.setName("Jhumpa Lahiri");
        TempModel d = new TempModel();
        d.setName("Hauki Murakami");
        TempModel e = new TempModel();
        e.setName("Shirish thorate");
        TempModel f = new TempModel();
        f.setName("Sydney Sheldon");
        TempModel g = new TempModel();
        g.setName("Nicholas Sparks");
        TempModel h = new TempModel();
        h.setName("Robin Cook");
        TempModel i = new TempModel();
        i.setName("Jeff Kinney");
        TempModel j = new TempModel();
        j.setName("Taslima Nasrin");
        TempModel k = new TempModel();
        k.setName("Stephanie Meyer");
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);
        list.add(f);
        list.add(g);
        list.add(h);
        list.add(i);
        list.add(j);
        list.add(k);
        listFavs = new ArrayList<TempModel>();
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
        tablayout.addTab(tablayout.newTab().setText("All"));
        tablayout.addTab(tablayout.newTab().setText("Favourites"));
    }

    private void setupViewPager() {
        final ViewPagerAdapter adapter = new ViewPagerAdapter
                (getSupportFragmentManager(), tablayout.getTabCount(),this);
        viewPager.setAdapter(adapter);
    }

    public  ArrayList<TempModel> getList() {
        return list;
    }
    public  ArrayList<TempModel> getLikeList() {
        return listFavs;
    }
}
