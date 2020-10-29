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

    @GET("bicycles/firebaseUserId/{firebaseId}")
    Call<List<Bike>> getBikesbyFirebaseId(@Path("firebaseId") String firebaseId);

    @POST("bicycles")
    @FormUrlEncoded
    Call<Bike> saveBike(@Field("Name") String name, @Field("Phone") String phone);

    @POST("bicycles/")
    Call<Bike> postBike(@Body Bike bike);

    @DELETE("bicycles/{id}")
    Call<Integer> deleteBike(@Path("id") int id);

}
