package com.example.theborak.connections;

import com.example.theborak.interfaces.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnectivity {
    private static final String BASE_URL = "https://www.getpostman.com/collections/";
    private static RetrofitConnectivity retrofitConnectivity;
    private Retrofit retrofit;

    private RetrofitConnectivity(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitConnectivity getRetrofitConnectivity(){
        if (retrofitConnectivity == null){
            retrofitConnectivity = new RetrofitConnectivity();
        }
        return retrofitConnectivity;
    }

    public Api api(){
        return retrofit.create(Api.class);
    }
}
