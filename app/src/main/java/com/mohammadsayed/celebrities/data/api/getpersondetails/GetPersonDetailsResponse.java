package com.mohammadsayed.celebrities.data.api.getpersondetails;

import com.google.gson.annotations.SerializedName;
import com.mohammadsayed.architecture.network.BaseResponse;
import com.mohammadsayed.celebrities.data.PersonDetails;

/**
 * Created by mohammad on 3/3/17.
 */

public class GetPersonDetailsResponse extends BaseResponse<GetPersonDetailsResponse> {

    private long id;

    @SerializedName("birthday")
    private String birthDay;

    @SerializedName("deathday")
    private String deathDay;

    private String name;

    private int gender;

    private String biography;

    @SerializedName("place_of_birth")
    private String placeOfBirth;

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

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getDeathDay() {
        return deathDay;
    }

    public void setDeathDay(String deathDay) {
        this.deathDay = deathDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
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

    public PersonDetails convertToPersonalDetails() {
        PersonDetails personDetails = new PersonDetails();
        personDetails.setId(getId());
        personDetails.setBirthDay(getBirthDay());
        personDetails.setDeathDay(getDeathDay());
        personDetails.setName(getName());
        personDetails.setGender(getGender());
        personDetails.setBiography(getBiography());
        personDetails.setPlaceOfBirth(getPlaceOfBirth());
        personDetails.setProfilePicture(getProfilePicture());
        personDetails.setPopularity(getPopularity());
        personDetails.setAdult(isAdult());
        return personDetails;
    }
}
