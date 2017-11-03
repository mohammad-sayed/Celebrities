package com.mohammadsayed.architecture.core;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mohammadsayed.architecture.utils.ObjectUtil;

/**
 * Created by mohammad on 1/15/17.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseViewCallback<P> {

    private P mPresenter;
    private String TAG = getFragmentTag();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = setUpPresenter();
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResource(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!ObjectUtil.isNull(mPresenter)) {
            mPresenter.setViewCallback(this);
        }
        initializeViewsAndData(view);
    }

    @Override
    public void setPresenter(P presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public P getPresenter() {
        return mPresenter;
    }

    protected abstract int getLayoutResource();

    public abstract String getFragmentTag();

    protected abstract void initializeViewsAndData(View view);

    protected <T> T caseInterface(Context context, Class<T> clazz) {
        T object = null;
        try {
            object = (T) context;
        } catch (ClassCastException ex) {
            throw new RuntimeException(context.getClass().getSimpleName() + "must implement" + clazz.getSimpleName());
        }
        return object;
    }

    protected void goToActivityForResult(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }

    protected void goToActivity(Intent intent) {
        if (getContext() == null) {
            return;
        }
        startActivity(intent);
    }
}
