package com.mohammadsayed.celebrities.explore;

import android.content.Context;

import com.mohammadsayed.architecture.core.OnServiceErrorListener;
import com.mohammadsayed.architecture.core.OnServiceSuccessListener;
import com.mohammadsayed.architecture.core.Repository;
import com.mohammadsayed.architecture.network.CoreError;
import com.mohammadsayed.architecture.network.volley.VolleyRequestManager;
import com.mohammadsayed.celebrities.Constants;
import com.mohammadsayed.celebrities.data.api.getpopularpersons.GetPersonsResponse;
import com.mohammadsayed.celebrities.data.api.searchpeople.SearchPeopleService;

/**
 * Created by mohammad on 1/25/17.
 */

public class ExploreRepository extends Repository<ExploreContract.PresenterCallback> implements ExploreContract.Repository<ExploreContract.PresenterCallback> {

    private int mPage = 0;
    private int mTotalPages = 1;
    private String mLastQuery;

    public ExploreRepository(Context context) {
        super(context);
    }

    @Override
    public void getPerson(String query) {
        mPage = 0;
        mTotalPages = 1;
        mLastQuery = query;
        final OnServiceErrorListener onServiceErrorListener = new OnServiceErrorListener() {
            @Override
            public void onError(CoreError error) {
                error.setStatusCode(Constants.ErrorCodes.EXPLORE_PERSONS);
                getPresenterCallback().onError(error);
            }
        };

        final OnServiceSuccessListener<GetPersonsResponse> onServiceSuccessListener = new OnServiceSuccessListener<GetPersonsResponse>() {
            @Override
            public void onSuccess(GetPersonsResponse response) {
                mPage = response.getPage();
                mTotalPages = response.getTotalPages();
                getPresenterCallback().onPersonsRetrieved(response.getPersonsList());
            }
        };

        SearchPeopleService service = new SearchPeopleService(getContext(), mLastQuery, mPage + 1, onServiceSuccessListener, onServiceErrorListener);
        service.start();
    }

    @Override
    public void loadMore() {
        if (mPage >= mTotalPages) {
            getPresenterCallback().onLoadMorePersonsRetrieved(null);
            return;
        }
        final OnServiceErrorListener onServiceErrorListener = new OnServiceErrorListener() {
            @Override
            public void onError(CoreError error) {
                error.setStatusCode(Constants.ErrorCodes.EXPLORE_PERSONS);
                getPresenterCallback().onError(error);
            }
        };

        final OnServiceSuccessListener<GetPersonsResponse> onServiceSuccessListener = new OnServiceSuccessListener<GetPersonsResponse>() {
            @Override
            public void onSuccess(GetPersonsResponse response) {
                mPage = response.getPage();
                mTotalPages = response.getTotalPages();
                getPresenterCallback().onLoadMorePersonsRetrieved(response.getPersonsList());
            }
        };
        SearchPeopleService service = new SearchPeopleService(getContext(), mLastQuery, mPage + 1, onServiceSuccessListener, onServiceErrorListener);
        service.start();
    }

    @Override
    public void cancelSearchPeopleRequest() {
        VolleyRequestManager.getInstance(getContext()).cancelRequest(SearchPeopleService.class.getSimpleName());
    }
}
