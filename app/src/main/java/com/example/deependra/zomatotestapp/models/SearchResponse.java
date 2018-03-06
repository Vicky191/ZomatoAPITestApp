package com.example.deependra.zomatotestapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deependra on 4/3/18.
 */

public class SearchResponse {
    @SerializedName("results_shown")
    private String resultShown;

    private ArrayList<Restaurants> restaurants;


    public String getResultShown() {
        return resultShown;
    }

    public void setResultShown(String resultShown) {
        this.resultShown = resultShown;
    }

    public ArrayList<Restaurants> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(ArrayList<Restaurants> restaurants) {
        this.restaurants = restaurants;
    }
}
