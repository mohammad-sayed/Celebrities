package com.mohammadsayed.architecture.utils;

import android.util.Log;

/**
 * Created by mohammad on 4/19/17.
 */

public class CLog {

    private static final boolean IS_LOG_ENABLED = false;

    public static void i(String tag, String msg) {
        if (IS_LOG_ENABLED) {
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (IS_LOG_ENABLED) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (IS_LOG_ENABLED) {
            Log.e(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (IS_LOG_ENABLED) {
            Log.v(tag, msg);
        }
    }
}
