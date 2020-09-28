package com.example.bicyclefinder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BikeService {

    //https://anbo-bicyclefinder.azurewebsites.net/api/bicycles
    @GET("bicycles")
    Call<List<Bike>> getAllBikes();

    //https://anbo-bicyclefinder.azurewebsites.net/api/bicycles/id/1
    @GET("bicycles/id/{id}")
    Call<Bike> getBikebyId(@Path("bikeId") int bikeId);

    //https://anbo-bicyclefinder.azurewebsites.net/api/bicycles/missing
    //https://anbo-bicyclefinder.azurewebsites.net/api/bicycles/found
    @GET("bicycles/{missingFound}")
    Call<List<Bike>> getBikebyMissingFound(@Path("missingFound") String missingFound);

    @POST("bicycles")
    @FormUrlEncoded
        // I had problems making this work. I used saveBookBody instead
    Call<Bike> saveUser(@Field("Name") String name, @Field("Phone") String phone);

    @POST("bicycles")
    Call<Bike> saveUser(@Body User user);


    //Har ikke brug for. Se https://anbo-bicyclefinder.azurewebsites.net/swagger/index.html
    //@DELETE("bicycles/{id}")
    //Call<Bike> deleteUser(@Path("id") int id);

    //@PUT("bicycles/{id}")
    //Call<Bike> updateBook(@Path("id") int id, @Body User user);
}
