package com.play4u.mobile.activities.strategies;

import android.util.Log;

import com.play4u.mobile.activities.MusicJockeySettingsActivity;
import com.play4u.mobile.services.MusicJockeySettingsService;

import org.json.JSONObject;

/**
 * Created by ykeyser on 8/10/15.
 */
public class MusicJockeySettingsStrategy extends UserSettingsStrategy {
    protected static final String LOG_TAG="ListenerSettingsCreate";

    public MusicJockeySettingsStrategy(final MusicJockeySettingsActivity activity, final MusicJockeySettingsService service){
        super(activity);
        super.setService(service);
    }

    public MusicJockeySettingsActivity getActivity(){
        return  (MusicJockeySettingsActivity)super.getActivity();
    }

    protected MusicJockeySettingsService getService(){
        return (MusicJockeySettingsService)super.getService();
    }

    protected void updateData(){
        super.updateData();
        updateStageName();
    }

    protected void updateStageName() {
        if(getActivity().getStageNameTextInput().isDirty()) {
            getService().setStageName(getActivity().getStageNameTextInput().toString());
        }
    }

    public void handleServiceResponse(final JSONObject jsonObj){
        if(jsonObj == null){
            throw new IllegalArgumentException("JSON response object is blank");
        }

        Log.i(LOG_TAG, "[ListenerSettingsCreateStrategy] handle json: "+jsonObj);

        return;
    }
}
