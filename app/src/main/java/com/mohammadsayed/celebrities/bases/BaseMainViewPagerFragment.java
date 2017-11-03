package com.mohammadsayed.celebrities.bases;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mohammadsayed.architecture.core.BasePresenter;
import com.mohammadsayed.celebrities.AppUtility;
import com.mohammadsayed.celebrities.R;

/**
 * Created by Mohammad Sayed on 11/3/2017.
 */

public abstract class BaseMainViewPagerFragment<P extends BasePresenter> extends BaseInternetFragment<P> {

    private String mPageTitle;
    private PersonAdapter mPersonsAdapter;

    public void setPageTitle(String pageTitle) {
        this.mPageTitle = pageTitle;
    }

    public String getPageTitle() {
        return mPageTitle;
    }

    @Override
    protected void initializeViewsAndData(View view) {
        super.initializeViewsAndData(view);
        initializeRecyclerView(view);
    }

    private void initializeRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_main);
        int spanCount = 2;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), spanCount);
        recyclerView.setLayoutManager(gridLayoutManager);
        int displayWidth = AppUtility.getDisplayWidth(getActivity());
        mPersonsAdapter = new PersonAdapter(getContext(), displayWidth, spanCount);
        recyclerView.setAdapter(mPersonsAdapter);
    }

    public PersonAdapter getPersonsAdapter() {
        return mPersonsAdapter;
    }
}
