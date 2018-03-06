package com.example.deependra.zomatotestapp;

import android.support.v7.widget.RecyclerView;

import com.example.deependra.zomatotestapp.BR;
import com.example.deependra.zomatotestapp.databinding.RestaurantItemBinding;

class RestaurantListingItemViewHolder extends RecyclerView.ViewHolder {
    private RestaurantItemBinding binding;

    RestaurantListingItemViewHolder(RestaurantItemBinding restaurantItemBinding) {
        super(restaurantItemBinding.getRoot());
        this.binding = restaurantItemBinding;

    }

    RestaurantItemBinding getBinding() {
        return binding;
    }
}
