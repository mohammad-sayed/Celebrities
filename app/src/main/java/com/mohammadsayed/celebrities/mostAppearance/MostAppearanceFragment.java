package com.mohammadsayed.celebrities.mostAppearance;

import android.view.View;

import com.mohammadsayed.celebrities.R;
import com.mohammadsayed.celebrities.bases.BaseMainViewPagerFragment;

public class MostAppearanceFragment extends BaseMainViewPagerFragment<MostAppearanceContract.Presenter>
        implements MostAppearanceContract.ViewCallback<MostAppearanceContract.Presenter> {

    public static MostAppearanceFragment getNewInstance() {
        return new MostAppearanceFragment();
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_most_appearance;
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
