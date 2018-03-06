package com.example.deependra.zomatotestapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by deependra on 4/3/18.
 */

public class UserRating {
    @SerializedName("ratingText")
    private String ratingText;

    @SerializedName("ratingColor")
    private String ratingColor;

    private String votes;

    @SerializedName("aggregateRating")
    private String aggregateRating;

    public String getRatingText()
    {
        return ratingText;
    }

    public void setRatingText(String ratingText)
    {
        this.ratingText = ratingText;
    }

    public String getRatingColor()
    {
        return ratingColor;
    }

    public void setRatingColor(String ratingColor)
    {
        this.ratingColor = ratingColor;
    }

    public String getVotes ()
    {
        return votes;
    }

    public void setVotes (String votes)
    {
        this.votes = votes;
    }

    public String getAggregateRating()
    {
        return aggregateRating;
    }

    public void setAggregateRating(String aggregateRating)
    {
        this.aggregateRating = aggregateRating;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [ratingText = "+ ratingText +", ratingColor = "+ ratingColor +", votes = "+votes+", aggregateRating = "+ aggregateRating +"]";
    }
}
