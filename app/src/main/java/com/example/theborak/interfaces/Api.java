package com.example.theborak.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("a1e9eb0628616c388618")
    Call<ResponseBody> create();
}
