package com.mohammadsayed.celebrities.bases;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mohammadsayed.architecture.core.BasePresenter;
import com.mohammadsayed.celebrities.R;

/**
 * Created by Mohammad Sayed on 11/3/2017.
 */

public abstract class BaseMainViewPagerFragment<P extends BasePresenter> extends BaseInternetFragment<P> {

    private String mPageTitle;
    private PersonAdapter mPersonAdapter;

    public void setPageTitle(String pageTitle) {
        this.mPageTitle = pageTitle;
    }

    public String getPageTitle() {
        return mPageTitle;
    }

    @Override
    protected void initializeViewsAndData(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_main);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        mPersonAdapter = new PersonAdapter(getContext());
        recyclerView.setAdapter(mPersonAdapter);
    }
}
