package com.play4u.mobile.activities.strategies;

import android.util.Log;

import com.play4u.mobile.activities.ListenerSettingsActivity;
import com.play4u.mobile.services.ListenerSettingsCreateService;

import org.json.JSONObject;

/**
 * Created by ykeyser on 8/10/15.
 */
public class ListenerSettingsCreateStrategy extends UserSettingsCreateStrategy{
    protected static final String LOG_TAG="ListenerSettingsCreate";

    public ListenerSettingsCreateStrategy(final ListenerSettingsActivity activity){
        super(activity);
    }

    public ListenerSettingsCreateService createService(){
        return (ListenerSettingsCreateService)getActivity().createService();
    }

    public void handleServiceResponse(final JSONObject jsonObj){
        if(jsonObj == null){
            throw new IllegalArgumentException("JSON response object is blank");
        }

        Log.i(LOG_TAG, "[ListenerSettingsCreateStrategy] handle json: "+jsonObj);

        return;
    }
}
