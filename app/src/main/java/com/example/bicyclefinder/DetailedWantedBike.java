package com.example.bicyclefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DetailedWantedBike extends AppCompatActivity {
    public static final String BIKE = "BIKE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_wanted_bike);
    }
}