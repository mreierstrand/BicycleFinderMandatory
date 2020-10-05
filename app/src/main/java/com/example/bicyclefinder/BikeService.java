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

    @GET("bicycles/")
    Call<List<Bike>> getAllBikes();

    @GET("bicycles/id/{id}")
    Call<Bike> getBikebyId(@Path("bikeId") int bikeId);

    @GET("bicycles/{missingFound}")
    Call<List<Bike>> getBikebyMissingFound(@Path("missingFound") String missingFound);

    @POST("bicycles")
    @FormUrlEncoded
    Call<Bike> saveBike(@Field("Name") String name, @Field("Phone") String phone);

    @POST("Bicycles/")
    Call<Bike> postBike(@Body Bike bike);

    @GET("users")
    Call<List<User>> getAllUsers();

    @GET("users/{userId}")
    Call<User> getUserById(@Path("userId") int id);

    @GET("users/firebaseuserid/{Id}")
    Call<User> getUserbyfirebaseId(@Path("Id") String firebaseId);


    @POST("users")
    @FormUrlEncoded
        // I had problems making this work. I used saveBookBody instead
    Call<User> saveUser(@Field("Name") String name, @Field("Phone") String phone);

    @POST("User")
    Call<User> saveUser(@Body User user);

    //Behøver ikke = https://anbo-bicyclefinder.azurewebsites.net/swagger/index.html
    //@DELETE("users/{userId}")
    //Call<User> deleteUser(@Path("id") int id);

    //@PUT("users/{userId}")
    //Call<User> updateBook(@Path("id") int id, @Body User user);


    //Har ikke brug for. Se https://anbo-bicyclefinder.azurewebsites.net/swagger/index.html
    //@DELETE("bicycles/{id}")
    //Call<Bike> deleteUser(@Path("id") int id);

    //@PUT("bicycles/{id}")
    //Call<Bike> updateBook(@Path("id") int id, @Body User user);
}
