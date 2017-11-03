package com.mohammadsayed.celebrities.mostAppearance;

import android.view.View;

import com.mohammadsayed.architecture.core.BaseFragment;
import com.mohammadsayed.celebrities.R;

public class MostAppearanceFragment extends BaseFragment<MostAppearanceContract.Presenter>
        implements MostAppearanceContract.ViewCallback<MostAppearanceContract.Presenter> {

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
    public MostAppearanceContract.Presenter setUpPresenter() {
        return new MostAppearancePresenter(getContext());
    }
}
