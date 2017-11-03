package com.mohammadsayed.architecture.main;

import com.mohammadsayed.architecture.R;
import com.mohammadsayed.architecture.core.BaseActivity;
import com.mohammadsayed.architecture.main.newslist.NewsListContract;
import com.mohammadsayed.architecture.main.newslist.NewsListPresenter;

public class NewsListActivity extends BaseActivity<NewsListContract.Presenter> {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_news_list;
    }

    @Override
    protected void initializeViewsAndData() {
    }

    @Override
    public NewsListContract.Presenter setUpPresenter() {
        return new NewsListPresenter(this);
    }
}
