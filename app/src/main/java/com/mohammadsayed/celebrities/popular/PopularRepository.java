package com.mohammadsayed.celebrities.popular;

import android.content.Context;

import com.mohammadsayed.architecture.core.Repository;

/**
 * Created by mohammad on 1/25/17.
 */

public class PopularRepository extends Repository<PopularContract.PresenterCallback> implements PopularContract.Repository<PopularContract.PresenterCallback> {

    public PopularRepository(Context context) {
        super(context);
    }
}
