package com.play4u.mobile.strategies;

import android.content.Intent;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.location.LocationServices;
import com.play4u.mobile.ListenerActivity;
import com.play4u.mobile.ListenerSettingsActivity;
import com.play4u.mobile.services.adapters.exceptions.ServiceCommitException;
import com.play4u.mobile.services.domain.GoogleApiClientSingleton;

import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * Created by ykeyser on 8/8/15.
 */
public class ListenerSettingsUpdateStrategy implements ActivityStrategy{
    protected final ListenerSettingsActivity activity;

    public ListenerSettingsUpdateStrategy(final ListenerSettingsActivity activity){
        this.activity=activity;
    }

    public void doStrategy(){
        updateFirstName();
        updateEmail();
        updateLocation();
        send();
        transitionToNextActivity();
    }

    protected void transitionToNextActivity(){
        final Intent intent = new Intent(activity, ListenerActivity.class);
        activity.startActivity(intent);
    }

    protected void send(){
        try {
            activity.getListenerSettings().send();
        }
        catch (ServiceCommitException ex){
            Log.w("ListenerSettings", ExceptionUtils.getStackTrace(ex));
        }
        catch (Throwable ex){
            Log.e("ListenerSettings", ExceptionUtils.getStackTrace(ex));
        }
    }

    protected void updateEmail(){
        if(activity.getEmailTextInput().isDirty()){
            activity.getListenerSettings().setEmail(activity.getEmailTextInput().toString());
        }
    }

    protected void updateFirstName(){
        if(activity.getFirstNameTextInput().isDirty()){
            activity.getListenerSettings().setFirstName(activity.getFirstNameTextInput().toString());
        }
    }

    protected void updateLocation(){
        final Location location= LocationServices.FusedLocationApi.getLastLocation(
                GoogleApiClientSingleton.singleton());

        activity.getListenerSettings().setLatitude((float)location.getLatitude())
                .setLongitude((float)location.getLatitude());
    }
}
