package com.mohammadsayed.celebrities.mostAppearance;

import android.view.View;
import android.widget.TextView;

import com.mohammadsayed.celebrities.R;
import com.mohammadsayed.celebrities.bases.BaseMainViewPagerFragment;
import com.mohammadsayed.celebrities.data.Person;

import java.util.List;

public class MostAppearanceFragment extends BaseMainViewPagerFragment<MostAppearanceContract.Presenter>
        implements MostAppearanceContract.ViewCallback<MostAppearanceContract.Presenter> {

    private TextView mTvNoResult;

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
    public MostAppearanceContract.Presenter setUpPresenter() {
        return new MostAppearancePresenter(getContext());
    }

    @Override
    protected void initializeViewsAndData(View view) {
        super.initializeViewsAndData(view);
        mTvNoResult = view.findViewById(R.id.most_appearance_tv_no_result);
        getPresenter().getMostAppearedPersons();
    }

    @Override
    public void showNoResultMessage(boolean show) {
        mTvNoResult.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setPersonsToList(List<Person> persons) {
        getPersonsAdapter().setPersonsList(persons);
    }
}
