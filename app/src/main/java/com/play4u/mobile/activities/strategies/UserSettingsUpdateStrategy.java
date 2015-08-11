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
        setService(activity.updateService());
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
        final Intent intent = new Intent(getActivity(), ListenerActivity.class);
        getActivity().startActivity(intent);
    }

    protected JSONObject send(){
        try {
            return getService().send();
        }
        catch (ServiceCommitException ex){
            Log.w(LOG_TAG, ExceptionUtils.getStackTrace(ex));
            return EmptyJSONObject.singleton();
        }
        catch (Throwable ex){
            Log.e(LOG_TAG, ExceptionUtils.getStackTrace(ex));
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
                .setLongitude((float)location.getLatitude());
    }
}