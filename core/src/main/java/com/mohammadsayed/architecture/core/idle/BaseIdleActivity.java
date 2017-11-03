package com.mohammadsayed.architecture.core.idle;

import com.mohammadsayed.architecture.core.BaseActivity;

/**
 * Created by Mohammad Sayed on 10/30/2017.
 */

public abstract class BaseIdleActivity extends BaseActivity<BaseIdleContract.Presenter>
        implements BaseIdleContract.ViewCallback<BaseIdleContract.Presenter> {

    @Override
    public BaseIdleContract.Presenter setUpPresenter() {
        return new BaseIdlePresenter(this);
    }
}
