package com.play4u.mobile.activities.strategies;

import android.util.Log;

import com.play4u.mobile.activities.MusicJockeySettingsActivity;
import com.play4u.mobile.services.MusicJockeySettingsService;

import org.json.JSONObject;

/**
 * Created by ykeyser on 8/10/15.
 */
public class MusicJockeySettingsCreateStrategy extends UserSettingsCreateStrategy{
    protected static final String LOG_TAG="ListenerSettingsCreate";

    public MusicJockeySettingsCreateStrategy(final MusicJockeySettingsActivity activity){
        super(activity);
    }

    public MusicJockeySettingsService createService(){
        return (MusicJockeySettingsService)getActivity().createService();
    }

    public void handleServiceResponse(final JSONObject jsonObj){
        if(jsonObj == null){
            throw new IllegalArgumentException("JSON response object is blank");
        }

        Log.i(LOG_TAG, "[ListenerSettingsCreateStrategy] handle json: "+jsonObj);

        return;
    }
}
