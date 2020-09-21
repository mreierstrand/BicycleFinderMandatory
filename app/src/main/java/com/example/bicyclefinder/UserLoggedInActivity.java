package com.example.bicyclefinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserLoggedInActivity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_logged_in);
//
//        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.mainSwipeRefresh);
//        swipeRefreshLayout.setOnRefreshListener(() -> {
//            getAndShowData();
//            swipeRefreshLayout.setRefreshing(false);
//        });
//    }
//
//    private void getAndShowData() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://jsonplaceholder.typicode.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        JsonService jsonService = retrofit.create(JsonService.class);
//
//    }



}