package com.mohammadsayed.celebrities.main;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mohammadsayed.celebrities.R;
import com.mohammadsayed.celebrities.bases.BaseMainViewPagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammad Sayed on 11/3/2017.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private List<BaseMainViewPagerFragment> mFragments;

    public MainViewPagerAdapter(FragmentManager fm, List<BaseMainViewPagerFragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public BaseMainViewPagerFragment getItem(int position) {
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

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        BaseMainViewPagerFragment fragment = getItem(position);
        if (fragment != null) {
            title = fragment.getPageTitle();
        }
        return title;
    }
}
