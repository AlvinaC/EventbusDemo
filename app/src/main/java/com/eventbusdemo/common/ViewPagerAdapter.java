package com.eventbusdemo.common;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.eventbusdemo.fragments.TabFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alvina.rasquinha on 27/03/18.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;
    private Activity context;

    public ViewPagerAdapter(FragmentManager fm, int NumOfTabs, Activity context) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TabFragment tab1 = new TabFragment();
                Bundle b1 = new Bundle();
                b1.putInt("which", 1);
                tab1.setArguments(b1);
                return tab1;
            case 1:
                TabFragment tab2 = new TabFragment();
                Bundle b2 = new Bundle();
                b2.putInt("which", 2);
                tab2.setArguments(b2);
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}


