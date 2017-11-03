package com.mohammadsayed.architecture.main.newslist;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.mohammadsayed.architecture.R;
import com.mohammadsayed.architecture.core.BaseFragment;
import com.mohammadsayed.architecture.data.News;

import java.util.ArrayList;

/**
 * Created by mohammad on 1/15/17.
 */

public class NewsListFragment extends BaseFragment<NewsListPresenter> implements NewsListContract.ViewCallback<NewsListPresenter>,
        View.OnClickListener,
        NewsListAdapter.OnNewsListAdapterListener {


    private NewsListAdapter mAdapter;

    @Override
    public String getFragmentTag() {
        return NewsListFragment.class.getSimpleName();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_news_list;
    }

    @Override
    public NewsListPresenter setUpPresenter() {
        return new NewsListPresenter(getContext());
    }

    @Override
    protected void initializeViewsAndData(View view) {
        RecyclerView rvNewsList = (RecyclerView) view.findViewById(R.id.rv_news_list);
        rvNewsList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvNewsList.setLayoutManager(linearLayoutManager);
        mAdapter = new NewsListAdapter(getContext(), new ArrayList<News>(), this);
        rvNewsList.setAdapter(mAdapter);
        view.findViewById(R.id.btn_perform_successful_action).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_perform_successful_action) {
            getNewsList();
        }
    }

    @Override
    public void onNewsClickListener(News news) {

    }

    private void getNewsList() {
        getPresenter().getNewsList();
    }

    @Override
    public void displayNews(ArrayList<News> newsList) {
        mAdapter.updateNews(newsList);
    }

    @Override
    public void displayError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

}
