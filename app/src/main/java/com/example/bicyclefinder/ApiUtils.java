package com.example.bicyclefinder;

class ApiUtils {
    private ApiUtils() {
    }

    private static final String BASE_URL = "https://anbo-bicyclefinder.azurewebsites.net/api/";

    public static BikeService getBikeService(){

        return RetrofitClient.getClient(BASE_URL).create(BikeService.class);
    }
}
