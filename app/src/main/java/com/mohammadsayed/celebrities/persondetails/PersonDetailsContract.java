package com.mohammadsayed.celebrities.persondetails;

import com.mohammadsayed.architecture.core.BaseInternetViewCallback;
import com.mohammadsayed.architecture.core.BasePresenter;
import com.mohammadsayed.architecture.core.BasePresenterCallback;
import com.mohammadsayed.architecture.core.BaseRepository;
import com.mohammadsayed.celebrities.data.Person;
import com.mohammadsayed.celebrities.data.PersonDetails;

import java.util.List;

/**
 * Created by Mohammad Sayed on 11/3/2017.
 */

public class PersonDetailsContract {

    public interface ViewCallback<P extends BasePresenter> extends BaseInternetViewCallback<P> {
        void displayPersonData(Person person);

        void displayPersonDetails(PersonDetails personDetails);

        void displayPersonImages(List<String> imagesPathList);
    }

    public interface Presenter<V extends ViewCallback, R extends BaseRepository> extends BasePresenter<V, R> {
        void getPersonDetails(Person person);
    }

    public interface PresenterCallback extends BasePresenterCallback {
        void onPersonDetailsRetrieved(PersonDetails personDetails);

        void onPersonImagesRetrieved(List<String> imagesPathList);
    }

    public interface Repository<P extends PresenterCallback> extends BaseRepository<P> {
        void getPersonDetails(Person person);

        void getPersonImages();
    }

}
