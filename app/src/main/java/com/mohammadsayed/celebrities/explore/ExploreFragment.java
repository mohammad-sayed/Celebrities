package com.mohammadsayed.celebrities.explore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.mohammadsayed.celebrities.AppUtility;
import com.mohammadsayed.celebrities.R;
import com.mohammadsayed.celebrities.bases.BaseMainViewPagerFragment;
import com.mohammadsayed.celebrities.data.Person;

import java.util.List;

public class ExploreFragment extends BaseMainViewPagerFragment<ExploreContract.Presenter>
        implements ExploreContract.ViewCallback<ExploreContract.Presenter> {

    private SearchView mSearchView;
    private TextView mTvNoResult;

    public static ExploreFragment getNewInstance() {
        return new ExploreFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_explore;
    }

    @Override
    public String getFragmentTag() {
        return getClass().getSimpleName();
    }

    @Override
    public ExploreContract.Presenter setUpPresenter() {
        return new ExplorePresenter(getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected void initializeViewsAndData(View view) {
        super.initializeViewsAndData(view);
        mTvNoResult = view.findViewById(R.id.explore_tv_no_result);
        addLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getPresenter().loadMore();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fragment_explore, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        mSearchView = (SearchView) menuItem.getActionView();
        mSearchView.setIconified(false);
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setQueryHint(getString(R.string.explore_type_in_search));
        menuItem.expandActionView();
        int width = AppUtility.getDisplayWidth(getActivity());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);
        mSearchView.setLayoutParams(params);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getPresenter().getPerson(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getPresenter().getPerson(newText);
                return false;
            }
        });
    }

    @Override
    public void setPersonsInList(List<Person> persons) {
        getPersonsAdapter().setPersonsList(persons);
    }

    @Override
    public void addMorePersonsToList(List<Person> persons) {
        getPersonsAdapter().addPersonsList(persons);
    }

    @Override
    public String getQuery() {
        return mSearchView.getQuery().toString();
    }

    @Override
    public void clearPersonsList() {
        getPersonsAdapter().clear();
    }

    @Override
    public int getPersonsCount() {
        return getPersonsAdapter().getItemCount();
    }

    @Override
    public void showNoResultMessage(boolean show) {
        mTvNoResult.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
