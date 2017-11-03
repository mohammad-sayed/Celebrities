package com.mohammadsayed.celebrities.popular;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mohammadsayed.architecture.core.BaseActivity;
import com.mohammadsayed.architecture.core.BaseFragment;
import com.mohammadsayed.celebrities.R;

public class PopularFragment extends BaseFragment<PopularContract.Presenter>
        implements PopularContract.ViewCallback<PopularContract.Presenter> {

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_popular;
    }

    @Override
    public String getFragmentTag() {
        return getClass().getSimpleName();
    }

    @Override
    protected void initializeViewsAndData(View view) {

    }

    @Override
    public PopularContract.Presenter setUpPresenter() {
        return new PopularPresenter(getContext());
    }
}
