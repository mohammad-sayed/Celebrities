package com.mohammadsayed.celebrities.explore;

import android.content.Context;

import com.mohammadsayed.architecture.core.Repository;

/**
 * Created by mohammad on 1/25/17.
 */

public class ExploreRepository extends Repository<ExploreContract.PresenterCallback> implements ExploreContract.Repository<ExploreContract.PresenterCallback> {

    public ExploreRepository(Context context) {
        super(context);
    }
}
