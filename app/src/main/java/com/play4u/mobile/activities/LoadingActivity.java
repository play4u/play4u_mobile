package com.play4u.mobile.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.play4u.mobile.R;
import com.play4u.mobile.domain.Listener;
import com.play4u.mobile.domain.MusicJockey;
import com.play4u.mobile.domain.User;
import com.play4u.mobile.services.tasks.GoogleApiConnectTask;
import com.play4u.mobile.util.BitMapRescaleFacade;
import com.play4u.mobile.util.GoogleApiClientSingleton;

import org.apache.commons.lang3.StringUtils;

public class LoadingActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    protected final String LOG_TAG="LoadingActivity";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        initializeLoadingBackground();
        initializeUser();
    }

    public void onConnected(final Bundle bundle) {
        Log.w(LOG_TAG, "Connected to Google API");

        final Location currentLocation = LocationServices.FusedLocationApi.getLastLocation(
                GoogleApiClientSingleton.singleton(this));

        User
            .singleton()
            .setLongitude((float) currentLocation.getLongitude())
            .setLatitude((float) currentLocation.getLatitude());
    }

    public void onConnectionSuspended(final int i) {
    }

    public void onConnectionFailed(final ConnectionResult connectionResult) {
        Log.w(LOG_TAG, "Could not connect to Google API services. Error code: " + connectionResult.getErrorCode());
    }

    protected void initializeUser(){
        final String user_type=getPreferences(MODE_PRIVATE).getString(User.USER_TYPE_KEY, User.USER_TYPE);

        if(StringUtils.equals(user_type, Listener.USER_TYPE_VALUE)){
            Listener.singleton(getPreferences(MODE_PRIVATE));
        }
        else if(StringUtils.equals(user_type, MusicJockey.USER_TYPE_VALUE)){
            MusicJockey.singleton(getPreferences(MODE_PRIVATE));
        }else {
            User.singleton(getPreferences(MODE_PRIVATE));
        }

        Log.i(LOG_TAG, "Detected user: " + User.singleton().getClass().getSimpleName());
    }

    protected void initializeLoadingBackground(){
        final View view = findViewById(R.id.loading_layout);
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.loading_background);

        view.setBackground(new BitmapDrawable(getResources(),
                new BitMapRescaleFacade(getWindowManager(), bitmap).rescaleImage()));
    }

    protected void connectToGoogleApi() {
        final GoogleApiConnectTask task=new GoogleApiConnectTask(this);
        task.execute();
    }

    public void onResume(){
        super.onResume();
        connectToGoogleApi();

        if (User.singleton() instanceof Listener){
            Log.i(LOG_TAG, "Detected listener. Switching to listener activity...");
            final Intent intent=new Intent(this, ListenerActivity.class);
            startActivity(intent);
        }
        else if (User.singleton() instanceof MusicJockey){
            Log.i(LOG_TAG, "Detected music jockey. Switching to music jockey activity...");
            final Intent intent=new Intent(this, MusicJockeyActivity.class);
            startActivity(intent);
        }
        else {
            Log.i(LOG_TAG,"User unknown. Switching to user selection activity...");
            final Intent intent = new Intent(this, SelectUserTypeActivity.class);
            startActivity(intent);
        }
    }
}
