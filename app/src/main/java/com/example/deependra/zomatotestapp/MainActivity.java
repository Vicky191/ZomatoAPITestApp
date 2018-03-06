package com.example.deependra.zomatotestapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.deependra.zomatotestapp.models.Restaurant;
import com.example.deependra.zomatotestapp.models.Restaurants;
import com.example.deependra.zomatotestapp.models.SearchResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText searchView;
    private ImageView resetSearchIcon;
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private RestaurantsListingAdapter mRestaurantsListingAdapter;
    private String mSearchQuery;
    private Disposable disposable;
    private Disposable timerDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }

    private void initViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        searchView = toolbar.findViewById(R.id.tvSearchText);
        resetSearchIcon = toolbar.findViewById(R.id.ivResetSearch);
        searchView.addTextChangedListener(new SearchWatcher());
        searchView.requestFocus();
        resetSearchIcon.setOnClickListener(this);
        searchView.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (!searchView.getText().toString().equals(mSearchQuery))
                    performSearch();
                searchView.clearFocus();
                mRecyclerView.requestFocus();
                hideKeyBoard();
                return true;
            }
            return false;
        });
        mRecyclerView = findViewById(R.id.restaurantList);
        mRecyclerView.setItemViewCacheSize(20);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRestaurantsListingAdapter = new RestaurantsListingAdapter(this);
        mRecyclerView.setAdapter(mRestaurantsListingAdapter);
    }

    private void performSearch() {
        AppUtil.disposeDisposable(disposable);
        mSearchQuery = searchView.getText().toString();
        APIInterface apiInterface = ServiceGenerator.createService(APIInterface.class);
        Observable<SearchResponse> searchResponseObservable = apiInterface.getRestaurantsObservable(mSearchQuery);
        disposable = searchResponseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(this::mapSearchResponse)
                .subscribe(this::handleSearchSuccess, e -> Log.e(TAG, e.toString()));
    }

    @NonNull
    private List mapSearchResponse(SearchResponse s) {
        Map<String, List<Restaurant>> cuisinesRestaurantMap = new HashMap<>();
        for (Restaurants restaurantItem : s.getRestaurants()) {
            for (String cuisine : restaurantItem.getRestaurant().getCuisines().split(",")) {
                if (!cuisinesRestaurantMap.containsKey(cuisine)) {
                    cuisinesRestaurantMap.put(cuisine, new ArrayList<>());
                }
                cuisinesRestaurantMap.get(cuisine).add(restaurantItem.getRestaurant());
            }
        }
        List restaurantsList = new ArrayList();
        for (Map.Entry<String, List<Restaurant>> entry : cuisinesRestaurantMap.entrySet()) {
            restaurantsList.add(entry.getKey());
            restaurantsList.addAll(entry.getValue());
        }
        return restaurantsList;
    }

    private void handleSearchSuccess(List response) {
        mRestaurantsListingAdapter.setData(response);
        mRestaurantsListingAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppUtil.disposeDisposable(timerDisposable);
        AppUtil.disposeDisposable(disposable);
    }

    private void hideKeyBoard() {
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (in != null && MainActivity.this.getCurrentFocus() != null) {
            in.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ivResetSearch) {
            searchView.setText("");
        }
    }

    private class SearchWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence c, int i, int i2, int i3) {
            // No implementation required
        }

        @Override
        public void onTextChanged(CharSequence c, int i, int i2, int i3) {
            // No implementation required
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String searchQuery = searchView.getText().toString();
            if (searchQuery.length() == 0) {
                resetSearchIcon.setVisibility(View.INVISIBLE);
            } else {
                resetSearchIcon.setVisibility(View.VISIBLE);
            }
            setTimer(searchQuery);
        }

        private void setTimer(String searchQuery) {
            AppUtil.disposeDisposable(timerDisposable);
            if (searchQuery.length() > 3 && !searchQuery.equals(mSearchQuery)) {
                timerDisposable = Observable.timer(500, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(s -> performSearch());
            }
        }
    }
}
