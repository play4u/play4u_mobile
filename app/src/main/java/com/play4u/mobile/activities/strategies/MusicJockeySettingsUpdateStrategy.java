package com.play4u.mobile.activities.strategies;

import android.content.Intent;

import com.play4u.mobile.activities.MusicJockeyActivity;
import com.play4u.mobile.activities.MusicJockeySettingsActivity;
import com.play4u.mobile.services.ListenerSettingsService;

import org.json.JSONObject;

/**
 * Created by ykeyser on 8/10/15.
 */
public class MusicJockeySettingsUpdateStrategy extends UserSettingsUpdateStrategy {
    protected ListenerSettingsService service;
    protected static final String LOG_TAG="MusicJockeySettings";
    protected MusicJockeySettingsActivity activity;

    public MusicJockeySettingsUpdateStrategy(final MusicJockeySettingsActivity activity){
        super(activity);
        this.activity=activity;
    }

    public void handleServiceResponse(final JSONObject jsonObj){
        return;
    }

    protected void updateData(){
        super.updateData();
        updateStageName();
    }

    protected void transitionToNextActivity(){
        final Intent intent = new Intent(activity, MusicJockeyActivity.class);
        activity.startActivity(intent);
    }


    protected void updateStageName(){
        if(activity.getStageNameTextInput().isDirty()){
            service.setFirstName(activity.getStageNameTextInput().toString());
        }
    }
}
