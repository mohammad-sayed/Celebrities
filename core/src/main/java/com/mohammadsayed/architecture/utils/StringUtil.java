package com.mohammadsayed.architecture.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

/**
 * Created by mohammad on 1/18/17.
 */

public class StringUtil {

    public static boolean isEmpty(String text, boolean trim) {
        if (text != null && trim) {
            text = text.trim();
        }
        return TextUtils.isEmpty(text);
    }

    public static void changeSubstringColor(TextView textView, String text, int colorStartIndex, int colorEndIndex, int colorHex) {
        Spannable spannable = getSpannable(text, colorStartIndex, colorEndIndex, colorHex);
        textView.setText(spannable, TextView.BufferType.SPANNABLE);
    }

    public static Spannable getSpannable(String text, int colorStartIndex, int colorEndIndex, int colorHex) {
        Spannable spannable = new SpannableString(text);
        spannable.setSpan(new ForegroundColorSpan(colorHex), colorStartIndex, colorEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    public static String getTextFromTextView(TextView textView, boolean trim) {
        String text = textView.getText().toString();
        if (trim) {
            text = text.trim();
        }
        return text;
    }

}
