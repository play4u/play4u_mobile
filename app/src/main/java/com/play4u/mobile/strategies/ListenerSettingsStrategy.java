package com.play4u.mobile.strategies;

import android.content.Intent;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.location.LocationServices;
import com.play4u.mobile.ListenerActivity;
import com.play4u.mobile.ListenerSettingsActivity;
import com.play4u.mobile.services.adapters.exceptions.ServiceCommitException;
import com.play4u.mobile.services.domain.singletons.GoogleApiClientSingleton;
import com.play4u.mobile.services.proxies.ListenerSettingsService;
import com.play4u.mobile.singletons.EmptyJSONObject;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONObject;

/**
 * Created by ykeyser on 8/10/15.
 */
public abstract class ListenerSettingsStrategy {
    protected final ListenerSettingsActivity activity;
    protected ListenerSettingsService service;
    protected static final String LOG_TAG="ListenerSettings";

    public ListenerSettingsStrategy(final ListenerSettingsActivity activity){
        this.activity=activity;
        this.service=createService();
    }

    public abstract ListenerSettingsService createService();

    public void handleServiceResponse(final JSONObject jsonObj){
        return;
    }

    public void doStrategy(){
        updateFirstName();
        updateEmail();
        updateLocation();
        handleServiceResponse(send());
        transitionToNextActivity();
    }

    protected void transitionToNextActivity(){
        final Intent intent = new Intent(activity, ListenerActivity.class);
        activity.startActivity(intent);
    }

    protected JSONObject send(){
        try {
            return service.send();
        }
        catch (ServiceCommitException ex){
            Log.w(LOG_TAG, ExceptionUtils.getStackTrace(ex));
            return EmptyJSONObject.singleton();
        }
        catch (Throwable ex){
            Log.e("ListenerSettings", ExceptionUtils.getStackTrace(ex));
            return EmptyJSONObject.singleton();
        }
    }

    protected void updateEmail(){
        if(activity.getEmailTextInput().isDirty()){
            service.setEmail(activity.getEmailTextInput().toString());
        }
    }

    protected void updateFirstName(){
        if(activity.getFirstNameTextInput().isDirty()){
            service.setFirstName(activity.getFirstNameTextInput().toString());
        }
    }

    protected void updateLocation(){
        final Location location= LocationServices.FusedLocationApi.getLastLocation(
                GoogleApiClientSingleton.singleton());

        service.setLatitude((float) location.getLatitude())
                .setLongitude((float)location.getLatitude());
    }
}
