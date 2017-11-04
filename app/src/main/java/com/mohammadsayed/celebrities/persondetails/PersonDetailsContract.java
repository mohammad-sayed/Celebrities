package com.mohammadsayed.celebrities.persondetails;

import com.mohammadsayed.architecture.core.BaseInternetViewCallback;
import com.mohammadsayed.architecture.core.BasePresenter;
import com.mohammadsayed.architecture.core.BasePresenterCallback;
import com.mohammadsayed.architecture.core.BaseRepository;
import com.mohammadsayed.celebrities.data.Person;
import com.mohammadsayed.celebrities.data.PersonDetails;
import com.mohammadsayed.celebrities.data.Photo;

import java.util.List;

/**
 * Created by Mohammad Sayed on 11/3/2017.
 */

public class PersonDetailsContract {

    public interface ViewCallback<P extends BasePresenter> extends BaseInternetViewCallback<P> {
        void displayPersonData(Person person);

        void displayPersonDetails(PersonDetails personDetails);

        void displayPersonImages(List<Photo> imagesPathList);
    }

    public interface Presenter<V extends ViewCallback, R extends BaseRepository> extends BasePresenter<V, R> {
        void savePresonDetails(Person person);

        void getPersonDetails();
    }

    public interface PresenterCallback extends BasePresenterCallback {
        void onPersonDetailsRetrieved(PersonDetails personDetails);

        void onPersonPhotosRetrieved(List<Photo> imagesPathList);
    }

    public interface Repository<P extends PresenterCallback> extends BaseRepository<P> {
        void savePersonDetails(Person person);

        void getPersonDetails();

        void getPersonPhotos();
    }

}
