package com.example.allenholmes.mygpsrunningtracker;

import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.util.Log;

public class LogLocationService extends Service {
    public LogLocationService() {
    }
    @Override
    public void onCreate() {
        super.onCreate();
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        MyLocationListener myLocationListener = new MyLocationListener();
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, myLocationListener);
        } catch (SecurityException e) {
            Log.d("debug", e.toString());
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new LogLocationServiceBinder();
    }
    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    private class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            Log.d("g53mdp", "location changed!");
            ContentValues values = new ContentValues();
            values.put(LogProviderContract.KEY_LATITUDE, location.getLatitude());
            values.put(LogProviderContract.KEY_LONGITUDE, location.getLongitude());
            values.put(LogProviderContract.KEY_ELEVATION, location.getAltitude());
            values.put(LogProviderContract.KEY_TIME, location.getTime());
            getContentResolver().insert(LogProviderContract.RUN_LOG_URI, values);
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
    public class LogLocationServiceBinder extends Binder implements IInterface {
        @Override
        public IBinder asBinder() {
            return this;
        }
    }

}


