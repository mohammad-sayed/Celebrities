package com.mohammadsayed.celebrities.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohammad Sayed on 11/3/2017.
 */

public class Person {
    private long id;

    private String name;

    @SerializedName("profile_path")
    private String profilePicture;

    private double popularity;

    @SerializedName("adult")
    private boolean isAdult;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }
}
