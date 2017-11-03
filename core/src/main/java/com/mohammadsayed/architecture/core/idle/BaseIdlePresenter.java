package com.mohammadsayed.architecture.core.idle;

import android.content.Context;
import android.support.design.widget.Snackbar;

import com.mohammadsayed.architecture.core.Presenter;
import com.mohammadsayed.architecture.network.CoreError;
import com.orgon.kazzeem.agent.Constants;
import com.orgon.kazzeem.agent.R;
import com.orgon.kazzeem.agent.data.Agent;

/**
 * Created by mohammad on 1/15/17.
 */

public class BaseIdlePresenter extends Presenter<BaseIdleContract.ViewCallback, BaseIdleContract.Repository>
        implements BaseIdleContract.Presenter<BaseIdleContract.ViewCallback, BaseIdleContract.Repository>,
        BaseIdleContract.PresenterCallback {

    public BaseIdlePresenter(Context context) {
        super(context);
    }

    @Override
    protected BaseIdleContract.Repository setUpRepository() {
        return new BaseIdleRepository(getContext());
    }

    //Repository Callbacks
    @Override
    public void onError(CoreError error) {
    }
}
