package com.example.bicyclefinder;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Bike implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("frameNumber")
    @Expose
    private String frameNumber;

    @SerializedName("kindOfBicycle")
    @Expose
    private String kindOfBicycle;

    @SerializedName("brand")
    @Expose
    private String brand;

    @SerializedName("colors")
    @Expose
    private String colors;

    @SerializedName("place")
    @Expose
    private String place;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("userId")
    @Expose
    private Integer userId;

    @SerializedName("missingFound")
    @Expose
    private String missingFound;

    public Bike(String frameNumber, String kindOfBicycle, String brand, String colors, String place, String date, String missingFound) {
        this.frameNumber = frameNumber;
        this.kindOfBicycle = kindOfBicycle;
        this.brand = brand;
        this.colors = colors;
        this.place = place;
        this.date = date;
        this.missingFound = missingFound;
    }

    public Bike() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(String frameNumber) {
        this.frameNumber = frameNumber;
    }

    public String getKindOfBicycle() {
        return kindOfBicycle;
    }

    public void setKindOfBicycle(String kindOfBicycle) {
        this.kindOfBicycle = kindOfBicycle;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMissingFound() {
        return missingFound;
    }

    public void setMissingFound(String missingFound) {
        this.missingFound = missingFound;
    }


    @NonNull
    @Override
    public String toString() {
        return id + " " + frameNumber + " " + kindOfBicycle + " " + brand + " " + colors + " " + place + " " + date + " " + userId + " " + missingFound; }
}