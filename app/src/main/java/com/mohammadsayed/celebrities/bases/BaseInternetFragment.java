package com.mohammadsayed.celebrities.bases;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ProgressBar;

import com.mohammadsayed.architecture.core.BaseFragment;
import com.mohammadsayed.architecture.core.BaseInternetViewCallback;
import com.mohammadsayed.architecture.core.BasePresenter;
import com.mohammadsayed.architecture.utils.CLog;
import com.mohammadsayed.architecture.utils.DeviceUtil;
import com.mohammadsayed.celebrities.R;

/**
 * Created by mohammad on 1/15/17.
 */

public abstract class BaseInternetFragment<P extends BasePresenter> extends BaseFragment<P> implements BaseInternetViewCallback<P> {
    private ProgressBar mProgressBar;
    private BroadcastReceiver networkReceiver;

    @Override
    protected void initializeViewsAndData(View view) {
        View loadingView = view.findViewById(R.id.pb_loading);
        if (loadingView == null) {
            throw new RuntimeException("Your fragment must have Progress bar with \"pb_loading\" id");
        }
        mProgressBar = (ProgressBar) loadingView;
    }

    @Override
    public void showSnackBar(final int messageStringId, final int duration) {
        if (getActivity() == null) {
            return;
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (getContext() == null || getView() == null) {
                    return;
                }
                Snackbar.make(getView(), messageStringId, duration).show();
            }
        });
    }

    @Override
    public void showSnackBar(int messageStringId, int duration, int actionStringId, final View.OnClickListener onClickListener) {
        showSnackBar(getString(messageStringId), duration, actionStringId, onClickListener);
    }

    @Override
    public void showSnackBar(final String message, final int duration, final int actionStringId, final View.OnClickListener onClickListener) {
        if (getActivity() == null) {
            return;
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (getContext() == null || getView() == null) {
                    return;
                }
                final Snackbar snackbar = Snackbar.make(getView(), message, duration);
                snackbar.setAction(actionStringId, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onClickListener != null) {
                            onClickListener.onClick(v);
                        }
                        snackbar.dismiss();
                    }
                }).setActionTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                snackbar.show();
            }
        });
    }

    @Override
    public void showLoadingIndicator(final boolean show) {
        if (getActivity() == null) {
            return;
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (getContext() == null) {
                    return;
                }
                mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    protected void setProgress(int progress) {
        mProgressBar.setProgress(progress);
    }

    protected void setMax(int max) {
        mProgressBar.setMax(max);
    }

    private void registerNetworkStateBroadcastReceiver() {
        if (networkReceiver == null) {
            networkReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    if (DeviceUtil.isOnline(context)) {
                        CLog.i("Internet", "Connected");
                        onInternetConnected();
                    } else {
                        CLog.i("Internet", "Disconnected");
                        onInternetDisconnected();
                    }
                }
            };
        }
        Activity activity = getActivity();
        if (activity != null) {
            IntentFilter filter = new IntentFilter();
            filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
            filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            getContext().registerReceiver(networkReceiver, filter);
        }
    }

    private void unregisterNetworkStateBroadcastReceiver() {
        if (getContext() != null) {
            getContext().unregisterReceiver(networkReceiver);
        }
    }

    protected void onInternetConnected() {
    }

    protected void onInternetDisconnected() {
    }
}
