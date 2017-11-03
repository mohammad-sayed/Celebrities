package com.mohammadsayed.architecture.core;

import com.mohammadsayed.architecture.network.CoreError;

/**
 * Created by mohammad on 1/16/17.
 */

public interface BasePresenterCallback {
    void onError(CoreError error);
}
