package com.example.bicyclefinder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class ApiUtils {
    private final Object BikeService;
    private static ApiUtils apiUtils = null;
    private BikeService bikeService = null;
    private static final String BASE_URL = "https://anbo-bicyclefinder.azurewebsites.net/api/";


    private ApiUtils() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://anbo-bicyclefinder.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BikeService = retrofit.create(BikeService.class);
    }

    public static ApiUtils GetInstance(){
        if (apiUtils == null) apiUtils = new ApiUtils();
        return apiUtils;
    }



    public static BikeService getBikeService(){

        return RetrofitClient.getClient(BASE_URL).create(BikeService.class);
    }


}
