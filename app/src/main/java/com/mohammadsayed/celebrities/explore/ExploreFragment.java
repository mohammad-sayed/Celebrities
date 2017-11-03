package com.mohammadsayed.celebrities.explore;

import android.view.View;

import com.mohammadsayed.architecture.core.BaseFragment;
import com.mohammadsayed.celebrities.R;

public class ExploreFragment extends BaseFragment<ExploreContract.Presenter>
        implements ExploreContract.ViewCallback<ExploreContract.Presenter> {

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_explore;
    }

    @Override
    public String getFragmentTag() {
        return getClass().getSimpleName();
    }

    @Override
    protected void initializeViewsAndData(View view) {

    }

    @Override
    public ExploreContract.Presenter setUpPresenter() {
        return new ExplorePresenter(getContext());
    }
}
