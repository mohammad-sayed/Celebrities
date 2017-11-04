package com.mohammadsayed.celebrities.data.api.getmoviecast;

import com.google.gson.annotations.SerializedName;
import com.mohammadsayed.architecture.network.BaseResponse;
import com.mohammadsayed.celebrities.data.Person;

import java.util.List;

/**
 * Created by mohammad on 3/3/17.
 */

public class GetMovieCreditsResponse extends BaseResponse<GetMovieCreditsResponse> {

    @SerializedName("cast")
    private List<Person> mPersonList;

    public List<Person> getPersonList() {
        return mPersonList;
    }

    public void setPersonList(List<Person> personList) {
        mPersonList = personList;
    }
}
