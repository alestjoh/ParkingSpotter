package com.example.parkingspotter.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SpotsApi {
    @GET("search?")
    Call<List<SpotData>> getNearbySpots(
            @Query("lat") double lat,
            @Query("lng") double lng);
}
