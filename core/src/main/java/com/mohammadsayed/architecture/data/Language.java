package com.mohammadsayed.architecture.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mohammad on 3/3/17.
 */

public class Language implements Parcelable {

    private String name;

    private String value;

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Language> CREATOR
            = new Creator<Language>() {
        public Language createFromParcel(Parcel in) {
            return new Language(in);
        }

        public Language[] newArray(int size) {
            return new Language[size];
        }
    };

    public Language() {
    }

    public Language(Parcel in) {
        setName(in.readString());
        setValue(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getName());
        dest.writeString(getValue());
    }

    public String getName() {
        return name;
    }

    public void setName(String key) {
        this.name = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
