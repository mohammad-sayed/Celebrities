package com.mohammadsayed.architecture.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.mohammadsayed.architecture.R;
import com.mohammadsayed.architecture.data.Language;

import java.util.Locale;

/**
 * Created by mohammad on 6/17/17.
 */

public class LocaleHelper {

    public static Context onAttach(Context context) {
        Language language = getPersistedData(context);
        return setLocale(context, language);
    }

    public static Context onAttach(Context context, Language defaultLanguage) {
        Language lang = getPersistedData(context, defaultLanguage);
        return setLocale(context, lang);
    }

    public static Language getLanguage(Context context) {
        return getPersistedData(context);
    }

    public static Context setLocale(Context context, Language language) {
        persist(context, language);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context, language);
        }

        return updateResourcesLegacy(context, language);
    }

    private static Language getPersistedData(Context context) {
        String languageString = PreferenceManager.getDefaultSharedPreferences(context).getString(Constants.SharedPreferences.LANGUAGE_TAG, null);
        Language language;
        if (languageString != null) {
            language = new Gson().fromJson(languageString, Language.class);
        } else {
            language = new Language();
            language.setName(context.getString(R.string.language_english));
            language.setValue(context.getString(R.string.locale_english));
        }
        return language;
    }

    private static Language getPersistedData(Context context, Language defaultLanguage) {
        String languageString = PreferenceManager.getDefaultSharedPreferences(context).getString(Constants.SharedPreferences.LANGUAGE_TAG, null);
        if (languageString != null) {
            return new Gson().fromJson(languageString, Language.class);
        } else {
            return defaultLanguage;
        }
    }

    private static void persist(Context context, Language language) {
        if (context == null) {
            return;
        }
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.SharedPreferences.LANGUAGE_TAG, new Gson().toJson(language));
        editor.apply();
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResources(Context context, Language language) {
        Locale locale = new Locale(language.getValue());
        Locale.setDefault(locale);

        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);

        return context.createConfigurationContext(configuration);
    }

    @SuppressWarnings("deprecation")
    private static Context updateResourcesLegacy(Context context, Language language) {
        Locale locale = new Locale(language.getValue());
        Locale.setDefault(locale);

        Resources resources = context.getResources();

        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale);
        }

        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        return context;
    }

    public static Locale getLocale(Context context) {
        Language language = getLanguage(context);
        Locale locale = new Locale(language.getValue());
        return locale;
    }
}
