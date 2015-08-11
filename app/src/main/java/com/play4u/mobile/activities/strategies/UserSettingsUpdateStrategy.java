package com.play4u.mobile.activities.strategies;

import android.content.Intent;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.location.LocationServices;
import com.play4u.mobile.activities.ListenerActivity;
import com.play4u.mobile.activities.UserSettingsActivity;
import com.play4u.mobile.services.UserSettingsService;
import com.play4u.mobile.services.exceptions.ServiceCommitException;
import com.play4u.mobile.util.EmptyJSONObject;
import com.play4u.mobile.util.GoogleApiClientSingleton;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONObject;

/**
 * Created by ykeyser on 8/10/15.
 */
public abstract class UserSettingsUpdateStrategy extends UserSettingsStrategy {
    protected UserSettingsService service;
    protected static final String LOG_TAG="ListenerSettings";

    public UserSettingsUpdateStrategy(final UserSettingsActivity activity){
        super(activity);
        this.service=activity.createService();
    }

    public void handleServiceResponse(final JSONObject jsonObj){
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

    protected void updateEmail() {
        if(activity.getEmailTextInput().isDirty()) {
            service.setEmail(activity.getEmailTextInput().toString());
        }
    }


    protected void updateLocation(){
        final Location location= LocationServices.FusedLocationApi.getLastLocation(
                GoogleApiClientSingleton.singleton());

        service.setLatitude((float) location.getLatitude())
                .setLongitude((float)location.getLatitude());
    }
}
