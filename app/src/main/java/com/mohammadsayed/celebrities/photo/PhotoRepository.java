package com.mohammadsayed.celebrities.photo;

import android.content.Context;

import com.mohammadsayed.architecture.core.Repository;

/**
 * Created by mohammad on 1/25/17.
 */

public class PhotoRepository extends Repository<PhotoContract.PresenterCallback> implements PhotoContract.Repository<PhotoContract.PresenterCallback> {

    public PhotoRepository(Context context) {
        super(context);
    }

}
