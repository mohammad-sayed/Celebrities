package com.mohammadsayed.celebrities;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;

/**
 * Created by Mohammad Sayed on 11/4/2017.
 */

public class AppUtility {

    public static int getDisplayWidth(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public static String getFullUrl(String imageSize, String imagePath) {
        String fullUrl = Constants.Image.BASE_IMAGES_URL + imageSize + imagePath;
        return fullUrl;
    }
}
