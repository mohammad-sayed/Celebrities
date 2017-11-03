package com.mohammadsayed.celebrities.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mohammadsayed.architecture.core.BaseActivity;
import com.mohammadsayed.architecture.core.idle.BaseIdleActivity;
import com.mohammadsayed.celebrities.R;

public class MainActivity extends BaseActivity<MainContract.Presenter>
        implements MainContract.ViewCallback<MainContract.Presenter> {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initializeViewsAndData() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public MainContract.Presenter setUpPresenter() {
        return new MainPresenter(this);
    }
}
