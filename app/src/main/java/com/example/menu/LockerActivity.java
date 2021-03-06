package com.example.menu;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class LockerActivity extends Activity  {
    String destino;
    private FusedLocationProviderClient fusedLocationClient;
    Location currentLocation;
    LatLng locationDestiny;
    Button abrirLocker;
    float results[];
    private ResultReceiver resultReceiver;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locker);
        Intent intent = getIntent();
        destino = intent.getExtras().getString("destino");
        String n = destino.substring(10,destino.length()-1);
        String[] latlong =  n.split(",");
        double latitude = Double.parseDouble(latlong[0]);
        double longitude = Double.parseDouble(latlong[1]);
        locationDestiny = new LatLng(latitude, longitude);


        abrirLocker = (Button)findViewById(R.id.abrirLocker);
        abrirLocker.setEnabled(false);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        ImageButton regresoLocker = findViewById(R.id.regresoLocker);
        regresoLocker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    private void fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            return;
        }
        Task<Location> task = fusedLocationClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
//                    Toast.makeText(getApplicationContext(), currentLocation.getLatitude() + "" + currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();
//                    locationDestiny = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());

                    Location destino = new Location("");
                    destino.setLatitude(locationDestiny.latitude);
                    destino.setLongitude(locationDestiny.longitude);
                    float distanceM = currentLocation.distanceTo(destino);
                    Log.i("l",currentLocation.toString());
                    Log.i("dis","ll  "+distanceM);
                    if(distanceM <= 2.0)
                        abrirLocker.setEnabled(true);
                    if(currentLocation.getLongitude()==locationDestiny.longitude&&currentLocation.getLatitude()==locationDestiny.latitude)
                    {
                        abrirLocker.setEnabled(true);

                        Toast.makeText(getApplicationContext(), "Able:", Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchLocation();
                }
                break;
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        fetchLocation();
        abrirLocker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Able", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

