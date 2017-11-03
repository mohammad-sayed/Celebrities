package com.mohammadsayed.celebrities.mostAppearance;

import android.content.Context;

import com.mohammadsayed.architecture.core.Repository;

/**
 * Created by mohammad on 1/25/17.
 */

public class MostAppearanceRepository extends Repository<MostAppearanceContract.PresenterCallback> implements MostAppearanceContract.Repository<MostAppearanceContract.PresenterCallback> {

    public MostAppearanceRepository(Context context) {
        super(context);
    }
}
