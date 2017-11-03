package com.mohammadsayed.architecture.utils;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;

/**
 * Created by mohammad on 5/27/17.
 */

public class RingtoneUtil {


    /**
     * @param vibrationPattern in milliseconds; if more than 0 the ringtone will be played with vibration, else it will just play a sound
     */
    public static void playRingtone(Context context, String url, long... vibrationPattern) {
        Ringtone ringtone = RingtoneManager.getRingtone(context, Uri.parse(url));
        if (ringtone == null) {
            return;
        }
        if (vibrationPattern != null) {
            Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(vibrationPattern, -1);
        }
        ringtone.play();
    }

    public static void playRingtone(Context context, int rawResId, long... vibrationPattern) {
        String url = "android.resource://" + context.getPackageName() + "/" + rawResId;
        playRingtone(context, url, vibrationPattern);
    }
}
