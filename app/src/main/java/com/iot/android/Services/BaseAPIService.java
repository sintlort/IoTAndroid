package com.iot.android.Services;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BaseAPIService {

    @FormUrlEncoded
    @POST("updateState")
    Call<ResponseBody> turnStateArduino(@Field("life") String life);

    @GET("readState")
    Call<ResponseBody> getStateArduino();

}
