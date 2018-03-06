package com.example.deependra.zomatotestapp;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deependra.zomatotestapp.databinding.RestaurantItemBinding;
import com.example.deependra.zomatotestapp.models.Restaurant;
import com.example.deependra.zomatotestapp.models.Restaurants;
import com.example.deependra.zomatotestapp.models.SearchResponse;
import com.squareup.picasso.Picasso;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.annotations.NonNull;

import static com.example.deependra.zomatotestapp.RestaurantsListingAdapter.ListingItemViewType.HEADER;
import static com.example.deependra.zomatotestapp.RestaurantsListingAdapter.ListingItemViewType.RESTRO_ITEM;

public class RestaurantsListingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private List restaurants;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({RESTRO_ITEM, HEADER})
    public @interface ListingItemViewType {
        int HEADER = 1;
        int RESTRO_ITEM = 2;

    }

    RestaurantsListingAdapter(Context context) {
        this.context = context;
    }

    public void setData(@NonNull List restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, @ListingItemViewType int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View view;
        switch (viewType) {
            case RESTRO_ITEM:
                RestaurantItemBinding restaurantItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.restaurant_item, parent, false);
                viewHolder = new RestaurantListingItemViewHolder(restaurantItemBinding);
                break;
            case HEADER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_header, parent, false);
                viewHolder = new RestroHeaderViewHolder(view);
                break;
            default:
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RestaurantListingItemViewHolder)
            bindRestroView((RestaurantListingItemViewHolder) holder, position);
        else {
            bindHeaderView((RestroHeaderViewHolder) holder, position);
        }

    }

    private void bindHeaderView(RestroHeaderViewHolder holder, int position) {
        holder.getTvHeader().setText(restaurants.get(position).toString());
    }

    private void bindRestroView(RestaurantListingItemViewHolder holder, int position) {
        RestaurantItemBinding binding = holder.getBinding();
        Restaurant restaurant = (Restaurant) restaurants.get(position);
        binding.setRestaurant(restaurant);
        String imageUrl = restaurant.getFeaturedImage();
        if (imageUrl != null && !"".equals(imageUrl)) {
            Picasso.with(context)
                    .load(imageUrl)
                    .fit()
                    .into(binding.ivFeaturedImage);
        } else binding.ivFeaturedImage.setVisibility(View.GONE);
        binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return AppUtil.isEmptyCollection(restaurants) ? 0 : restaurants.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (AppUtil.isEmptyCollection(restaurants)) {
            return -1;
        }
        if (restaurants.get(position) instanceof Restaurant) {
            return RESTRO_ITEM;
        } else {
            return HEADER;
        }
    }
}
