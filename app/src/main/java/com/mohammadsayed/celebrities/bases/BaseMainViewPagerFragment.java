package com.mohammadsayed.celebrities.bases;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mohammadsayed.architecture.core.BasePresenter;
import com.mohammadsayed.celebrities.AppUtility;
import com.mohammadsayed.celebrities.R;
import com.mohammadsayed.celebrities.components.EndlessRecyclerOnScrollListener;

/**
 * Created by Mohammad Sayed on 11/3/2017.
 */

public abstract class BaseMainViewPagerFragment<P extends BasePresenter> extends BaseInternetFragment<P> {

    private String mPageTitle;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
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
        mRecyclerView = view.findViewById(R.id.recycler_view_main);
        int spanCount = 2;
        mGridLayoutManager = new GridLayoutManager(getContext(), spanCount);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        int displayWidth = AppUtility.getDisplayWidth(getActivity());
        mPersonsAdapter = new PersonAdapter(getContext(), displayWidth, spanCount);
        mRecyclerView.setAdapter(mPersonsAdapter);
    }

    protected void addLoadMoreListener(final OnLoadMoreListener onLoadMoreListener) {
        if (mRecyclerView == null || mGridLayoutManager == null) {
            return;
        }
        mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(mGridLayoutManager) {
            @Override
            public void onLoadMore() {
                if (onLoadMoreListener != null) {
                    onLoadMoreListener.onLoadMore();
                }
            }
        });
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public PersonAdapter getPersonsAdapter() {
        return mPersonsAdapter;
    }
}
