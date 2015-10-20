package com.simbiosyscorp.tutorials.currentlocation;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements LocationListener {
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
    //Accessing the Location Manager
        final LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
       if( !locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
           Log.i("@@@@@@@@@@","HERE");
           Toast.makeText(getApplicationContext(),"Wifi not available",Toast.LENGTH_SHORT).show();
     return; }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
    //Getting current Location with .getLastKnownLocation() function
    // of LocationManager. This example uses Wifi
                Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (location != null) {
                    //If a valid Loaction is returned
                    textView.setText("Your Location (Latitude,Longitude) : (" + location.getLatitude() + "," + location.getLongitude() + ")");
                } else {
                    textView.setText("Location Could not be determined.");
                }
            }
        });

        //Using LocationManager, we can get updates on user location at regurlar intervals (3 seconds here).

        locationManager.requestLocationUpdates(locationManager.NETWORK_PROVIDER, 3000, 0, this);

    }

//This activity implements athe LocationListener interface,
// which has the following callBack Function


    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            textView.setText("Location Update: Your Location (Latitude,Longitude) : (" + location.getLatitude() + "," + location.getLongitude() + ")");
        } else {
            textView.setText("Location Could not be determined.");
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
