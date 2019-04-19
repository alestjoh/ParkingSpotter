package com.example.parkingspotter.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private static final double DEFAULT_LAT = 37.422, DEFAULT_LNG = -122.084;

    private MutableLiveData<Double>
            userLatitude = new MutableLiveData<>(),
            userLongitude = new MutableLiveData<>(),
            searchLat = new MutableLiveData<>(),
            searchLng = new MutableLiveData<>();

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
}
