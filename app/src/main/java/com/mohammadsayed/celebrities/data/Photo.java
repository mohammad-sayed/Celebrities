package com.mohammadsayed.celebrities.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohammad Sayed on 11/4/2017.
 */

public class Photo implements Parcelable {

    private float width;

    private float height;

    @SerializedName("file_path")
    private String photoPath;

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Photo> CREATOR
            = new Creator<Photo>() {
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

    public Photo() {
    }

    public Photo(Parcel in) {
        setWidth(in.readFloat());
        setHeight(in.readFloat());
        setPhotoPath(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(getWidth());
        dest.writeFloat(getHeight());
        dest.writeString(getPhotoPath());
    }


    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
