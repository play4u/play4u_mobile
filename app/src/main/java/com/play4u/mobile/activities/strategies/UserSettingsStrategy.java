package com.play4u.mobile.activities.strategies;

import android.location.Location;
import android.util.Log;

import com.google.android.gms.location.LocationServices;
import com.play4u.mobile.activities.UserSettingsActivity;
import com.play4u.mobile.services.UserSettingsService;
import com.play4u.mobile.services.exceptions.ServiceCommitException;
import com.play4u.mobile.services.singletons.GoogleApiClientSingleton;
import com.play4u.mobile.util.EmptyJSONObject;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONObject;

/**
 * Created by ykeyser on 8/10/15.
 */
public abstract class UserSettingsStrategy {
    protected static final String LOG_TAG="ListenerSettings";
    protected UserSettingsActivity activity;
    protected UserSettingsService service;

    public UserSettingsActivity getActivity(){
        return activity;
    }

    protected void setService(final UserSettingsService service){
        this.service=service;
    }

    protected UserSettingsService getService(){
        return service;
    }

    protected UserSettingsStrategy(final UserSettingsActivity activity){
        this.activity=activity;
    }

    protected void handleServiceResponse(final JSONObject jsonObj){
        return;
    }

    protected void updateData(){
        updateEmail();
        updateLocation();
    }

    public void doStrategy(){
        updateData();
        handleServiceResponse(send());
        transitionToNextActivity();
    }

    protected abstract void transitionToNextActivity();

    protected JSONObject send(){
        try {
            return getService().send();
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

    protected void updateEmail() {
        if(getActivity().getEmailTextInput().isDirty()) {
            getService().setEmail(getActivity().getEmailTextInput().toString());
        }
    }


    protected void updateLocation(){
        final Location location= LocationServices.FusedLocationApi.getLastLocation(
                GoogleApiClientSingleton.singleton());

        getService().setLatitude((float) location.getLatitude())
                .setLongitude((float)location.getLongitude());
    }
}
