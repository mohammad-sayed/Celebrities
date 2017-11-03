package com.mohammadsayed.architecture.core;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.mohammadsayed.architecture.utils.LocaleHelper;
import com.mohammadsayed.architecture.utils.ObjectUtil;

/**
 * Created by mohammad on 1/15/17.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseViewCallback<P> {

    private P mPresenter;
    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature();
        setContentView(getLayoutResId());
        mPresenter = setUpPresenter();
        if (!ObjectUtil.isNull(mPresenter)) {
            mPresenter.setViewCallback(this);
        }
        initializeViewsAndData();
    }

    @Override
    public void setPresenter(P presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public P getPresenter() {
        return mPresenter;
    }

    protected void requestWindowFeature() {
    }

    abstract protected int getLayoutResId();

    abstract protected void initializeViewsAndData();

    protected void goToActivity(Class ActivityClass, boolean finish) {
        Intent intent = new Intent(this, ActivityClass);
        startActivity(intent);
        if (finish) {
            finish();
        }
    }

    protected void goToActivityForResult(Class ActivityClass, int requestCode) {
        Intent intent = new Intent(this, ActivityClass);
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }
}
