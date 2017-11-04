package com.mohammadsayed.celebrities.data.api.getpopularpersons;

import com.google.gson.annotations.SerializedName;
import com.mohammadsayed.architecture.network.BaseResponse;
import com.mohammadsayed.celebrities.data.Person;

import java.util.ArrayList;

/**
 * Created by mohammad on 3/3/17.
 */

public class GetPersonsResponse extends BaseResponse<GetPersonsResponse> {

    private int page;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private ArrayList<Person> personsList;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public ArrayList<Person> getPersonsList() {
        return personsList;
    }

    public void setPersonsList(ArrayList<Person> personsList) {
        this.personsList = personsList;
    }
}
