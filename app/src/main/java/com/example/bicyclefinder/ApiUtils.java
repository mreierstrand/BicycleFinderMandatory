package com.example.bicyclefinder;

class ApiUtils {
    private ApiUtils() {
    }

    private static final String BASE_URL = "https://anbo-bicyclefinder.azurewebsites.net/api/users/";

    public static UserService getUserService(){

        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }
}
