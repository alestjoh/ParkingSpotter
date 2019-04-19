package com.example.parkingspotter.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.parkingspotter.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

/**
 * Notes:
 * Search area, by location, date, time, will be a Fragment.
 * Fragment will be added to the back stack when opened.
 * Fragment will be opened on app startup.
 * "Search" button will call Fragment's finish... maybe
 *
 * Bottom bar buttons will not be functional
 *
 * Main window will be another fragment, consisting of the map
 * Main Activity will try to get user permissions to start map location
 *
 * Ask Antonino about map icon detail pop-up
 *
 * Reservation confirmation pop-up: another Fragment, probably
 *
 * Spot details seems to be a new activity
 * Details activity can also spawn confirmation fragment
 *
 * Example API Spot Search: http://ridecellparking.herokuapp.com/api/v1/parkinglocations/search?lat=0.0000&lng=0.0000
 *
 * Views:
 *      Activities:
 *          Main Map
 *          Spot Details
 *      Fragments:
 *          Search Specs
 *          Map
 *          (Map Icon Pop-up?)
 *          Reservation Confirmation
 * Models:
 *      Spot Data (Retrofit @Entitiy)
 *      Reservation Data (For POSTing)
 *      API Interface
 *      Retrofit helper to GET spots and POST reservations to database
 * ViewModel Tasks:
 *      Get Live list of SpotDatas for populating the map based on user search location
 *      Post reservation with details when user presses "Pay to Reserve" from either Activity
 */
public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_LOCATION_PERMISSIONS = 17;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkFineLocationPermissions();
    }

    private void checkFineLocationPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSIONS);
        } else {
            updateDeviceLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_LOCATION_PERMISSIONS &&
                grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            updateDeviceLocation();
        }
    }

    private void updateDeviceLocation() {
        FusedLocationProviderClient fusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location -> {

            });
        }

        //TODO: Inform viewmodel of device location
    }
}
