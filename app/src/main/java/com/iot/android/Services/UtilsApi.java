package com.iot.android.Services;

public class UtilsApi {
    // 10.0.2.2 ini adalah localhost.
    public static final String BASE_URL_API = "https://arduinoiot1000.000webhostapp.com/api/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseAPIService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseAPIService.class);
    }
}
