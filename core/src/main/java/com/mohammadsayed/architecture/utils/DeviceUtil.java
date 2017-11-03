package com.mohammadsayed.architecture.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by mohammad on 5/12/17.
 */

public class DeviceUtil {

    public static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }


    public static float dpToPx(Context context, float dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return dp * displayMetrics.density;
    }

    public static float pxToDp(Context context, float px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return px * displayMetrics.density;
    }

    public static void hideSoftKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        hideSoftKeyboard(activity, view);
    }

    public static void hideSoftKeyboard(Context context, View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
