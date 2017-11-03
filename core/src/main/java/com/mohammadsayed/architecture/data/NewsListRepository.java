package com.mohammadsayed.architecture.data;

import android.content.Context;

import com.mohammadsayed.architecture.core.Repository;
import com.mohammadsayed.architecture.core.OnServiceErrorListener;
import com.mohammadsayed.architecture.core.OnServiceSuccessListener;
import com.mohammadsayed.architecture.main.newslist.NewsListContract;
import com.mohammadsayed.architecture.network.CoreError;
import com.mohammadsayed.architecture.services.GetNewsListService;

/**
 * Created by mohammad on 1/15/17.
 */

public class NewsListRepository extends Repository<NewsListContract.PresenterCallback> implements NewsListContract.Repository<NewsListContract.PresenterCallback> {

    public NewsListRepository(Context context) {
        super(context);
    }

    //Step4_callService
    @Override
    public void getNewsList() {
        OnServiceSuccessListener<News[]> onServiceSuccessListener = new OnServiceSuccessListener<News[]>() {
            @Override
            public void onSuccess(News[] news) {
                //onNewsListRetrieved
                getPresenterCallback().onNewsListRetrieved(news);
            }
        };

        OnServiceErrorListener onServiceErrorListener = new OnServiceErrorListener() {
            @Override
            public void onError(CoreError error) {
                getPresenterCallback().onError(error);
            }
        };

        GetNewsListService getNewsListService = new GetNewsListService(getContext(),
                onServiceSuccessListener, onServiceErrorListener);
        getNewsListService.start();
    }
}
