package sopt.seouri.application;

import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.util.Set;

import sopt.seouri.home.HomeFragment;

/**
 * Created by ash on 2017-10-27.
 */

public class GPSTracker extends Service implements LocationListener {

    private final Context mContext;

    // flag for GPS status
    boolean isGPSEnabled = false;

    // flag for network status
    boolean isNetworkEnabled = false;

    // flag for GPS status
    boolean canGetLocation = false;

    Location location; // location
    double latitude; // latitude
    double longitude; // longitude

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

    // Declaring a Location Manager
    protected LocationManager locationManager;

    private Handler mHandler;

    public GPSTracker(Context context, Handler handler) {
        this.mContext = context;
        this.mHandler = handler;
        getLocation();
    }
    public void Update(){
        getLocation();
    }
    public Location getLocation() {

        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( mContext, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
            return null;
        }
        try {
            locationManager = (LocationManager) mContext
                    .getSystemService(LOCATION_SERVICE);

            // getting GPS status
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                this.canGetLocation = true;
                // First get location from Network Provider
                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    Log.d("Network", "Network");
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }

    /**
     * Stop using GPS listener
     * Calling this function will stop using GPS in your app
     * */
    public void stopUsingGPS(){
        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( mContext, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
            return;
        }
        if(locationManager != null){
            locationManager.removeUpdates(GPSTracker.this);
        }
    }

    /**
     * Function to get latitude
     * */
    public double getLatitude(){
        if(location != null){
            latitude = location.getLatitude();
        }

        // return latitude
        return latitude;
    }

    /**
     * Function to get longitude
     * */
    public double getLongitude(){
        if(location != null){
            longitude = location.getLongitude();
        }

        // return longitude
        return longitude;
    }

    /**
     * Function to check GPS/wifi enabled
     * @return boolean
     * */
    public boolean canGetLocation() {
        return this.canGetLocation;
    }

    /**
     * Function to show settings alert dialog
     * On pressing Settings button will lauch Settings Options
     * */
    public void showSettingsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        // Setting Dialog Title
        alertDialog.setTitle("GPS 설정을 해주시기 바랍니다.");

        // Setting Dialog Message
        alertDialog.setMessage("GPS가 설정되어 있지 않습니다. 설정화면으로 이동하시겠습니까?");

        // On pressing Settings button
        alertDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });

        // on pressing cancel button
        alertDialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    @Override
    public void onLocationChanged(Location location) {
        if(location != null){
            double latitude= location.getLatitude();
            double longitude = location.getLongitude();
            //Toast.makeText(mContext, "onLocationChanged is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_SHORT).show();
            sendString("onLocationChanged is - \nLat: " + latitude + "\nLong: " + longitude + " provider:"+location.getProvider()+" mock:"+location.isFromMockProvider());
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
        //Toast.makeText(mContext, "onProviderDisabled " + provider, Toast.LENGTH_SHORT).show();
        mHandler.sendEmptyMessage(HomeFragment.RENEW_GPS);
        sendString( "onProviderDisabled " + provider);
    }

    @Override
    public void onProviderEnabled(String provider) {
        //Toast.makeText(mContext, "onProviderEnabled " + provider, Toast.LENGTH_SHORT).show();
        mHandler.sendEmptyMessage(HomeFragment.RENEW_GPS);
        sendString( "onProviderEnabled " + provider);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        //Toast.makeText(mContext, "onStatusChanged " + provider + " : " + status, Toast.LENGTH_SHORT).show();
        mHandler.sendEmptyMessage(HomeFragment.RENEW_GPS);
        sendString("onStatusChanged " + provider + " : " + status + ":" + printBundle(extras));
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    private void sendString(String str){
        Message msg = mHandler.obtainMessage();
        msg.what = HomeFragment.SEND_PRINT;
        msg.obj = new String(str);
        mHandler.sendMessage(msg);
    }

    public static String printBundle(Bundle extras) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("extras = " + extras);
            sb.append("\n");
            if (extras != null) {
                Set keys = extras.keySet();
                sb.append("++ bundle key count = " + keys.size());
                sb.append("\n");

                for (String _key : extras.keySet()) {
                    sb.append("key=" + _key + " : " + extras.get(_key)+",");
                }
                sb.append("\n");
            }
        } catch (Exception e) {

        } finally {

        }
        return sb.toString();
    }

}