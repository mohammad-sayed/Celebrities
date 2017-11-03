package com.mohammadsayed.architecture.main.newslist;

import com.mohammadsayed.architecture.core.BasePresenter;
import com.mohammadsayed.architecture.core.BasePresenterCallback;
import com.mohammadsayed.architecture.core.BaseRepository;
import com.mohammadsayed.architecture.core.BaseViewCallback;
import com.mohammadsayed.architecture.data.News;

import java.util.ArrayList;

/**
 * Created by mohammad on 1/15/17.
 */

public class NewsListContract {

    public interface ViewCallback<P extends BasePresenter> extends BaseViewCallback<P> {

        void displayNews(ArrayList<News> newsList);

        void displayError(String errorMessage);

    }

    public interface Presenter<V extends ViewCallback, R extends BaseRepository> extends BasePresenter<V, R> {
        void getNewsList();
    }

    public interface PresenterCallback extends BasePresenterCallback {
        void onNewsListRetrieved(News[] newsList);
    }

    public interface Repository<P extends PresenterCallback> extends BaseRepository<P> {
        void getNewsList();

    }
}
