package com.mohammadsayed.celebrities.mostAppearance;

import android.content.Context;

import com.mohammadsayed.architecture.core.Presenter;
import com.mohammadsayed.architecture.network.CoreError;

/**
 * Created by mohammad on 1/15/17.
 */

public class MostAppearancePresenter extends Presenter<MostAppearanceContract.ViewCallback, MostAppearanceContract.Repository>
        implements MostAppearanceContract.Presenter<MostAppearanceContract.ViewCallback, MostAppearanceContract.Repository>,
        MostAppearanceContract.PresenterCallback {

    public MostAppearancePresenter(Context context) {
        super(context);
    }

    @Override
    protected MostAppearanceContract.Repository setUpRepository() {
        return new MostAppearanceRepository(getContext());
    }

    //Repository Callbacks
    @Override
    public void onError(CoreError error) {
    }
}
