package com.mohammadsayed.celebrities.main;

import android.content.Context;

import com.mohammadsayed.architecture.core.Repository;

import java.util.ArrayList;

/**
 * Created by mohammad on 1/25/17.
 */

public class MainRepository extends Repository<MainContract.PresenterCallback> implements MainContract.Repository<MainContract.PresenterCallback> {

    private final String TAG = MainRepository.class.getSimpleName();
    private ArrayList<String> mListeningEvents = new ArrayList<>();

    public MainRepository(Context context) {
        super(context);
    }
}
