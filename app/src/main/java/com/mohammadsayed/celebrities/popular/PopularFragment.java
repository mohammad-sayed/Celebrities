package com.mohammadsayed.celebrities.popular;

import android.view.View;

import com.mohammadsayed.celebrities.R;
import com.mohammadsayed.celebrities.bases.BaseMainViewPagerFragment;
import com.mohammadsayed.celebrities.data.Person;

import java.util.List;

public class PopularFragment extends BaseMainViewPagerFragment<PopularContract.Presenter>
        implements PopularContract.ViewCallback<PopularContract.Presenter> {

    public static PopularFragment getNewInstance() {
        return new PopularFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_popular;
    }

    @Override
    public String getFragmentTag() {
        return getClass().getSimpleName();
    }

    @Override
    public PopularContract.Presenter setUpPresenter() {
        return new PopularPresenter(getContext());
    }

    @Override
    protected void initializeViewsAndData(View view) {
        super.initializeViewsAndData(view);
        getPresenter().getPopularPeople();
    }

    @Override
    public void addPopularPeopleToList(List<Person> persons) {
        getPersonsAdapter().addPersonsList(persons);
    }
}
