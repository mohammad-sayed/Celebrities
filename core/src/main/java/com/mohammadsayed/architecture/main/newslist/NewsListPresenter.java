package com.mohammadsayed.architecture.main.newslist;

import android.content.Context;

import com.mohammadsayed.architecture.core.Presenter;
import com.mohammadsayed.architecture.data.News;
import com.mohammadsayed.architecture.data.NewsListRepository;
import com.mohammadsayed.architecture.network.CoreError;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by mohammad on 1/15/17.
 */

public class NewsListPresenter extends Presenter<NewsListFragment, NewsListRepository>
        implements NewsListContract.Presenter<NewsListFragment, NewsListRepository>,
        NewsListContract.PresenterCallback {

    public NewsListPresenter(Context context) {
        super(context);
    }


    @Override
    protected NewsListRepository setUpRepository() {
        return new NewsListRepository(getContext());
    }

    @Override
    public void onNewsListRetrieved(News[] newsList) {
        if (getViewCallback() != null) {
            ArrayList<News> newsArrayList = new ArrayList<>(Arrays.asList(newsList));
            getViewCallback().displayNews(newsArrayList);
        }
    }

    @Override
    public void getNewsList() {
        getRepository().getNewsList();
    }

    public void onError(CoreError error) {
        if (getViewCallback() != null) {
            getViewCallback().displayError(error.getMessage());
        }
    }
}
