package com.mohammadsayed.celebrities.photo;

import com.mohammadsayed.architecture.core.BaseInternetViewCallback;
import com.mohammadsayed.architecture.core.BasePresenter;
import com.mohammadsayed.architecture.core.BasePresenterCallback;
import com.mohammadsayed.architecture.core.BaseRepository;
import com.mohammadsayed.celebrities.data.Photo;

/**
 * Created by Mohammad Sayed on 11/3/2017.
 */

public class PhotoContract {

    public interface ViewCallback<P extends BasePresenter> extends BaseInternetViewCallback<P> {
        void requestPermission(String permission, int requestCode, int title, int description);
    }

    public interface Presenter<V extends ViewCallback, R extends BaseRepository> extends BasePresenter<V, R> {
        void savePhoto(Photo photo);

        void savePhotoLocally();

        void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);
    }

    public interface PresenterCallback extends BasePresenterCallback {
        void onPhotoSavedLocally();
    }

    public interface Repository<P extends PresenterCallback> extends BaseRepository<P> {
        void savePhoto(Photo photo);

        void savePhotoLocally();
    }

}
