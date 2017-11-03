package com.mohammadsayed.celebrities.popular;

import android.content.Context;

import com.mohammadsayed.architecture.core.OnServiceErrorListener;
import com.mohammadsayed.architecture.core.OnServiceSuccessListener;
import com.mohammadsayed.architecture.core.Repository;
import com.mohammadsayed.architecture.network.CoreError;
import com.mohammadsayed.celebrities.Constants;
import com.mohammadsayed.celebrities.data.api.getpopularpersons.GetPopularPersonsResponse;
import com.mohammadsayed.celebrities.data.api.getpopularpersons.GetPopularPersonsService;

/**
 * Created by mohammad on 1/25/17.
 */

public class PopularRepository extends Repository<PopularContract.PresenterCallback> implements PopularContract.Repository<PopularContract.PresenterCallback> {

    private int mPage = 0;
    private int mTotalPages = 1;

    public PopularRepository(Context context) {
        super(context);
    }

    @Override
    public void getPopularPeople() {
        if (mPage >= mTotalPages) {
            getPresenterCallback().onPopularPeopleRetrieved(null);
            return;
        }
        final OnServiceErrorListener onServiceErrorListener = new OnServiceErrorListener() {
            @Override
            public void onError(CoreError error) {
                error.setStatusCode(Constants.ErrorCodes.GET_POPULAR_PEOPLE);
                getPresenterCallback().onError(error);
            }
        };

        final OnServiceSuccessListener<GetPopularPersonsResponse> onServiceSuccessListener = new OnServiceSuccessListener<GetPopularPersonsResponse>() {
            @Override
            public void onSuccess(GetPopularPersonsResponse response) {
                mPage = response.getPage();
                mTotalPages = response.getTotalPages();
                getPresenterCallback().onPopularPeopleRetrieved(response.getPersonsList());
            }
        };

        GetPopularPersonsService service = new GetPopularPersonsService(getContext(), mPage + 1, onServiceSuccessListener, onServiceErrorListener);
        service.start();
    }
}
