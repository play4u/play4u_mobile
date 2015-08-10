package com.play4u.mobile.services.domain;

import android.app.Activity;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * Created by ykeyser on 8/10/15.
 */
public class GoogleApiClientSingleton {
    protected static volatile GoogleApiClient helper;
    protected static final String LOG_TAG="GoogleApiClientSingle";

    public static GoogleApiClient singleton(final Activity activity){
        if(!(activity instanceof GoogleApiClient.ConnectionCallbacks)){
            throw new IllegalArgumentException("Activity does not implement Google API connection-callbacks");
        }
        else if(!(activity instanceof GoogleApiClient.OnConnectionFailedListener)){
            throw new IllegalArgumentException("Activity does not implement Google API on-connection-failed listener");
        }

        GoogleApiClient result = helper;
        if (result == null) {
            synchronized(GoogleApiClientSingleton.class) {
                result = helper;

                if (result == null) {
                    try {
                        helper = result = new GoogleApiClient.Builder(activity)
                                .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks)activity)
                                .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) activity)
                                .addApi(LocationServices.API)
                                .build();
                    }
                    catch (Throwable ex){
                        Log.e(LOG_TAG, ExceptionUtils.getStackTrace(ex));
                    }
                }
            }
        }
        return result;
    }

    public static GoogleApiClient singleton(){
        return GoogleApiClientSingleton.singleton(null);
    }
}
