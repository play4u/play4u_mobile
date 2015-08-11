package com.play4u.mobile.services.domain.singletons;

import android.content.Context;
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

    protected static void checkContextParam(final Context context){
        if(!(context instanceof GoogleApiClient.ConnectionCallbacks)){
            throw new IllegalArgumentException("Context does not implement Google API connection-callbacks. " +
                    "Type: "+context.getClass().getName());
        }
        else if(!(context instanceof GoogleApiClient.OnConnectionFailedListener)){
            throw new IllegalArgumentException("Context does not implement Google API on-connection-failed listener. " +
                    "Type: "+context.getClass().getName());
        }
    }

    public static GoogleApiClient singleton(final Context context){
        GoogleApiClient result = helper;
        if (result == null) {
            synchronized(GoogleApiClientSingleton.class) {
                result = helper;

                if (result == null) {
                    try {
                        checkContextParam(context);
                        helper = result = new GoogleApiClient.Builder(context)
                                .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks)context)
                                .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) context)
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
