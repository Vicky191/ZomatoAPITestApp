package com.example.deependra.zomatotestapp;

import com.example.deependra.zomatotestapp.models.SearchResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("search")
    Observable<SearchResponse> getRestaurantsObservable(@Query("q") String query);
}
