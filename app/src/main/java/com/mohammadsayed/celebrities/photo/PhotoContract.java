package com.mohammadsayed.celebrities.photo;

import com.mohammadsayed.architecture.core.BaseInternetViewCallback;
import com.mohammadsayed.architecture.core.BasePresenter;
import com.mohammadsayed.architecture.core.BasePresenterCallback;
import com.mohammadsayed.architecture.core.BaseRepository;

/**
 * Created by Mohammad Sayed on 11/3/2017.
 */

public class PhotoContract {

    public interface ViewCallback<P extends BasePresenter> extends BaseInternetViewCallback<P> {
    }

    public interface Presenter<V extends ViewCallback, R extends BaseRepository> extends BasePresenter<V, R> {
    }

    public interface PresenterCallback extends BasePresenterCallback {
    }

    public interface Repository<P extends PresenterCallback> extends BaseRepository<P> {
    }

}
