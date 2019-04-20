package com.example.parkingspotter.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.parkingspotter.model.SpotData;
import com.example.parkingspotter.model.SpotsApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainViewModel extends ViewModel {
    private static final double DEFAULT_LAT = 37.422, DEFAULT_LNG = -122.084;

    private MutableLiveData<Double>
            userLatitude = new MutableLiveData<>(),
            userLongitude = new MutableLiveData<>(),
            searchLat = new MutableLiveData<>(),
            searchLng = new MutableLiveData<>();

    private MutableLiveData<List<SpotData>> spots = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    private SpotsApi api = null;

    public MainViewModel() {
        userLatitude.postValue(DEFAULT_LAT);
        userLongitude.postValue(DEFAULT_LNG);
        searchLat.postValue(DEFAULT_LAT);
        searchLng.postValue(DEFAULT_LNG);
    }

    public LiveData<Double> getUserLatitude() {
        return userLatitude;
    }

    public LiveData<Double> getUserLongitude() {
        return userLongitude;
    }

    public MutableLiveData<Double> getSearchLng() {
        return searchLng;
    }

    public MutableLiveData<Double> getSearchLat() {
        return searchLat;
    }

    public LiveData<List<SpotData>> getSpots() {
        return spots;
    }

    public LiveData<String> getError() {
        return error;
    }

    public void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ridecellparking.herokuapp.com/api/v1/parkinglocations/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(SpotsApi.class);
    }

    public void searchForSpots(double lat, double lng) {
        api.getNearbySpots(lat, lng).enqueue(new Callback<List<SpotData>>() {
            @Override
            public void onResponse(Call<List<SpotData>> call, Response<List<SpotData>> response) {
                if (response.body() != null) {
                    spots.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<SpotData>> call, Throwable t) {
                error.postValue(t.getMessage());
            }
        });
    }
}
