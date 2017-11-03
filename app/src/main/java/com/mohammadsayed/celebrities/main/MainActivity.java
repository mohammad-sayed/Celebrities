package com.mohammadsayed.celebrities.main;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.mohammadsayed.architecture.core.BaseActivity;
import com.mohammadsayed.celebrities.R;
import com.mohammadsayed.celebrities.bases.BaseMainViewPagerFragment;
import com.mohammadsayed.celebrities.explore.ExploreFragment;
import com.mohammadsayed.celebrities.mostAppearance.MostAppearanceFragment;
import com.mohammadsayed.celebrities.popular.PopularFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainContract.Presenter>
        implements MainContract.ViewCallback<MainContract.Presenter> {

    private MainViewPagerAdapter mMainViewPagerAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public MainContract.Presenter setUpPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void initializeViewsAndData() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewPager mainViewPager = findViewById(R.id.view_pager_main);
        TabLayout mainTabLayout = findViewById(R.id.tabs_main);
        List<BaseMainViewPagerFragment> fragments = createViewPagerFragments();
        mMainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), fragments);
        mainViewPager.setAdapter(mMainViewPagerAdapter);
        mainTabLayout.setupWithViewPager(mainViewPager);
    }


    private List<BaseMainViewPagerFragment> createViewPagerFragments() {
        List<BaseMainViewPagerFragment> fragments = new ArrayList<>();
        BaseMainViewPagerFragment baseMainViewPagerFragment = PopularFragment.getNewInstance();
        baseMainViewPagerFragment.setPageTitle(getString(R.string.home_title_popular));
        fragments.add(baseMainViewPagerFragment);
        baseMainViewPagerFragment = ExploreFragment.getNewInstance();
        baseMainViewPagerFragment.setPageTitle(getString(R.string.home_title_explore));
        fragments.add(baseMainViewPagerFragment);
        baseMainViewPagerFragment = MostAppearanceFragment.getNewInstance();
        baseMainViewPagerFragment.setPageTitle(getString(R.string.home_title_most_appearance));
        fragments.add(baseMainViewPagerFragment);
        return fragments;
    }

}
