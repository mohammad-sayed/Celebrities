package com.mohammadsayed.celebrities.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mohammadsayed.architecture.core.BaseFragment;

import java.util.List;

/**
 * Created by Mohammad Sayed on 11/3/2017.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    public MainViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        if (mFragments != null && !mFragments.isEmpty() && position < mFragments.size()) {
            return mFragments.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        if (mFragments != null) {
            return mFragments.size();
        }
        return 0;
    }
}
