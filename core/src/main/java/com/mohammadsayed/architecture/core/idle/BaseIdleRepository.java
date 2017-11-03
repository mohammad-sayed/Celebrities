package com.mohammadsayed.architecture.core.idle;

import android.content.Context;

import com.mohammadsayed.architecture.core.Repository;

import java.util.ArrayList;

/**
 * Created by mohammad on 1/25/17.
 */

public class BaseIdleRepository extends Repository<BaseIdleContract.PresenterCallback> implements BaseIdleContract.Repository<BaseIdleContract.PresenterCallback> {

    private final String TAG = BaseIdleRepository.class.getSimpleName();
    private ArrayList<String> mListeningEvents = new ArrayList<>();

    public BaseIdleRepository(Context context) {
        super(context);
    }
}
