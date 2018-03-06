package com.example.deependra.zomatotestapp;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class ServiceGenerator {
    private static final String BASE_URL = "https://developers.zomato.com/api/v2.1/";
    private static final String ZOMATO_API_KEY = "user-key";
    private static final String ZOMATO_API_VALUE = "0a9aca93846671c10f437df60aa26435";

    private static OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(chain -> {
        Request originalRequest = chain.request();
        Request.Builder builder = originalRequest.newBuilder().header(ZOMATO_API_KEY,
                ZOMATO_API_VALUE);

        Request newRequest = builder.build();
        return chain.proceed(newRequest);
    }).build();

    private static Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL).client(okHttpClient).build();

    // No need to instantiate this class.
    private ServiceGenerator() {

    }

    static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
