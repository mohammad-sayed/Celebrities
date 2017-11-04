package com.mohammadsayed.celebrities.data.api.getPersonPhotos;

import com.google.gson.annotations.SerializedName;
import com.mohammadsayed.architecture.network.BaseResponse;
import com.mohammadsayed.celebrities.data.Photo;

import java.util.List;

/**
 * Created by mohammad on 3/3/17.
 */

public class GetPersonPhotosResponse extends BaseResponse<GetPersonPhotosResponse> {

    @SerializedName("profiles")
    private List<Photo> mPhotos;

    public List<Photo> getPhotos() {
        return mPhotos;
    }

    public void setPhotos(List<Photo> photos) {
        mPhotos = photos;
    }
}
