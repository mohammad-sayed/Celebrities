package com.mohammadsayed.architecture.core;

import com.mohammadsayed.architecture.network.CoreError;

/**
 * Created by mohammad on 1/23/17.
 */

public interface OnServiceErrorListener {
    void onError(CoreError error);
}
