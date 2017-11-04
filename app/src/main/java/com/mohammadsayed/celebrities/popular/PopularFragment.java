package com.mohammadsayed.celebrities.popular;

import android.content.Intent;
import android.view.View;

import com.mohammadsayed.celebrities.Constants;
import com.mohammadsayed.celebrities.R;
import com.mohammadsayed.celebrities.bases.BaseMainViewPagerFragment;
import com.mohammadsayed.celebrities.data.Person;
import com.mohammadsayed.celebrities.persondetails.PersonDetailsActivity;

import java.util.List;

public class PopularFragment extends BaseMainViewPagerFragment<PopularContract.Presenter>
        implements PopularContract.ViewCallback<PopularContract.Presenter>, BaseMainViewPagerFragment.OnLoadMoreListener {

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
        addLoadMoreListener(this);
    }

    @Override
    public void onLoadMore() {
        getPresenter().getMorePopularPeople();
    }

    @Override
    public void addPopularPeopleToList(List<Person> persons) {
        getPersonsAdapter().addPersonsList(persons);
    }

    @Override
    public void onPersonSelected(Person person) {
        Intent intent = new Intent(getContext(), PersonDetailsActivity.class);
        intent.putExtra(Constants.ExtrasKeys.PERSON, person);
        goToActivity(intent);
    }
}
