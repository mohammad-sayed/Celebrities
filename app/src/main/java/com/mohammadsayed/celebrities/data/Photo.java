package com.mohammadsayed.celebrities.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohammad Sayed on 11/4/2017.
 */

public class Photo {

    private float width;

    private float height;

    @SerializedName("file_path")
    private String photoPath;

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
