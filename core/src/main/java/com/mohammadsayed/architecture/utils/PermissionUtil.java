package com.mohammadsayed.architecture.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import com.mohammadsayed.architecture.R;

/**
 * Created by mohammad on 5/14/17.
 */

public class PermissionUtil {

    /*// Assume thisActivity is the current activity
    public static boolean checkCallPermission(Context context) {
        int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE);
        return isPermitted(permissionCheck);
    }*/

    // Assume thisActivity is the current activity
    public static boolean checkPermission(Context context, String permission) {
        int permissionCheck = ContextCompat.checkSelfPermission(context, permission);
        return isPermitted(permissionCheck);
    }

    private static boolean isPermitted(int permissionCheck) {
        switch (permissionCheck) {
            case PackageManager.PERMISSION_GRANTED:
                return true;
            default:
                return false;
        }
    }

    public static void requestPermission(final Activity activity, final String permission, final int permissionRequestCode, int explanationTitleResId, int explanationDescriptionResId) {
        // Here, thisActivity is the current activity
        if (!checkPermission(activity, permission)) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    permission)) {
                new AlertDialog.Builder(activity)
                        .setTitle(explanationTitleResId)
                        .setMessage(explanationDescriptionResId)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermission(activity, permission, permissionRequestCode);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create()
                        .show();
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.
                requestPermission(activity, permission, permissionRequestCode);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

    public static void requestPermission(final Fragment fragment, final String permission, final int permissionRequestCode, int explanationTitleResId, int explanationDescriptionResId) {
        if (fragment.getContext() == null) {
            return;
        }
        // Here, thisActivity is the current activity
        if (!checkPermission(fragment.getContext(), permission)) {
            // Should we show an explanation?
            if (fragment.shouldShowRequestPermissionRationale(permission)) {
                new AlertDialog.Builder(fragment.getContext())
                        .setTitle(explanationTitleResId)
                        .setMessage(explanationDescriptionResId)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermission(fragment, permission, permissionRequestCode);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                fragment.onRequestPermissionsResult(permissionRequestCode, new String[]{permission}, new int[]{PackageManager.PERMISSION_DENIED});
                                dialog.dismiss();
                            }
                        })
                        .create()
                        .show();
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.
                requestPermission(fragment, permission, permissionRequestCode);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

    private static void requestPermission(Activity activity, String permission, int permissionRequestCode) {
        ActivityCompat.requestPermissions(activity,
                new String[]{permission},
                permissionRequestCode);
    }

    private static void requestPermission(Fragment fragment, String permission, int permissionRequestCode) {
        fragment.requestPermissions(new String[]{permission},
                permissionRequestCode);
    }

}
