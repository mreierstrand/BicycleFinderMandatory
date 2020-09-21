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

public interface UserService {

        @GET("users")
        Call<List<User>> getAllUsers();

        @GET("users/{userId}")
        Call<User> getUserById(@Path("Id") int id);

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
}
