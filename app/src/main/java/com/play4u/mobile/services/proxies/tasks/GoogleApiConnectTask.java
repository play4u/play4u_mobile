package com.play4u.mobile.services.proxies.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.play4u.mobile.services.domain.singletons.GoogleApiClientSingleton;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by ykeyser on 8/10/15.
 */
public class GoogleApiConnectTask extends AsyncTask<Void,Void,GoogleApiClient>{
    protected static final String LOG_TAG="GoogleApiConnectTask";
    public static final int GOOGLE_API_CONNECTION_RETRIES=3;
    public static final short GOOGLE_API_CONNECTION_TIMEOUT=3; // secs
    protected Context ctx;

    public GoogleApiConnectTask(final Context ctx){
        this.ctx=ctx;
    }

    public GoogleApiClient doInBackground(final Void... params){
        try{
            int apiAvailabilityStatusCode=GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(ctx);
            int errorCode= ConnectionResult.INTERNAL_ERROR;

            if(apiAvailabilityStatusCode==ConnectionResult.SUCCESS){
                Log.i(LOG_TAG,"Google API available");
            }else{
                Log.e(LOG_TAG,"Google API unavailable. Error code: "+apiAvailabilityStatusCode);
                throw new IOException("Google API unavailable");
            }

            Log.i(LOG_TAG,"Connecting to Google API...");

            for(int attempt=1; attempt <= GOOGLE_API_CONNECTION_RETRIES && errorCode != ConnectionResult.SUCCESS; attempt++){
                errorCode= GoogleApiClientSingleton
                        .singleton(ctx)
                        .blockingConnect(GOOGLE_API_CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                        .getErrorCode();

                if(errorCode != ConnectionResult.SUCCESS) {
                    Log.w(LOG_TAG, "Timed out trying to connect to Google API services. Error code: " +
                            errorCode + ". Attempt: " + attempt);
                }
            }

            if(errorCode != ConnectionResult.SUCCESS){
                throw new IOException("Connection to Google APIs failed. Error code: "+errorCode);
            }else{
                Log.i(LOG_TAG,"Connected to Google API.");
                return GoogleApiClientSingleton.singleton();
            }
        }
        catch (Exception ex){
            Log.e(LOG_TAG, ExceptionUtils.getStackTrace(ex));
            return null;
        }
    }
}
