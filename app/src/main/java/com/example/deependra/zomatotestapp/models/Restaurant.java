package com.example.deependra.zomatotestapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by deependra on 4/3/18.
 */

public class Restaurant {

    private Location location;

    private String currency;

    private String apikey;

    private String id;

    private String price_range;

    private String name;

    private String cuisines;

    private String photos_url;

    @SerializedName("featured_image")
    private String featuredImage;

    private String book_url;

    private String menu_url;

    private String is_delivering_now;

    private String switch_to_order_menu;

    private String url;

    private String deeplink;

    private String average_cost_for_two;

    private String thumb;

    private String has_table_booking;

    public Location getLocation ()
    {
        return location;
    }

    public void setLocation (Location location)
    {
        this.location = location;
    }

    public String currency ()
    {
        return currency;
    }

    public void setCurrency (String currency)
    {
        this.currency = currency;
    }

    public String getApikey ()
    {
        return apikey;
    }

    public void setApikey (String apikey)
    {
        this.apikey = apikey;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getPrice_range ()
    {
        return price_range;
    }

    public void setPrice_range (String price_range)
    {
        this.price_range = price_range;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getCuisines ()
    {
        return cuisines;
    }

    public void setCuisines (String cuisines)
    {
        this.cuisines = cuisines;
    }

    public String getPhotos_url ()
    {
        return photos_url;
    }

    public void setPhotos_url (String photos_url)
    {
        this.photos_url = photos_url;
    }

    public String getCurrency() {
        return currency;
    }

    public String getFeaturedImage() {
        return featuredImage;
    }

    public void setFeaturedImage(String featuredImage) {
        this.featuredImage = featuredImage;
    }

    public String getBook_url ()
    {
        return book_url;
    }

    public void setBook_url (String book_url)
    {
        this.book_url = book_url;
    }

    public String getMenu_url ()
    {
        return menu_url;
    }

    public void setMenu_url (String menu_url)
    {
        this.menu_url = menu_url;
    }

    public String getIs_delivering_now ()
    {
        return is_delivering_now;
    }

    public void setIs_delivering_now (String is_delivering_now)
    {
        this.is_delivering_now = is_delivering_now;
    }

    public String getSwitch_to_order_menu ()
    {
        return switch_to_order_menu;
    }

    public void setSwitch_to_order_menu (String switch_to_order_menu)
    {
        this.switch_to_order_menu = switch_to_order_menu;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    public String getDeeplink ()
    {
        return deeplink;
    }

    public void setDeeplink (String deeplink)
    {
        this.deeplink = deeplink;
    }

    public String getAverage_cost_for_two ()
    {
        return average_cost_for_two;
    }

    public void setAverage_cost_for_two (String average_cost_for_two)
    {
        this.average_cost_for_two = average_cost_for_two;
    }

    public String getThumb ()
    {
        return thumb;
    }

    public void setThumb (String thumb)
    {
        this.thumb = thumb;
    }

    public String getHas_table_booking ()
    {
        return has_table_booking;
    }

    public void setHas_table_booking (String has_table_booking)
    {
        this.has_table_booking = has_table_booking;
    }
}
