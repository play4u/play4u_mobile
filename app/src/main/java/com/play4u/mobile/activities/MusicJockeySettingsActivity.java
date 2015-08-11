package com.play4u.mobile.activities;

import android.os.Bundle;
import android.widget.EditText;

import com.play4u.mobile.R;
import com.play4u.mobile.activities.strategies.MusicJockeySettingsCreateStrategy;
import com.play4u.mobile.activities.strategies.MusicJockeySettingsUpdateStrategy;
import com.play4u.mobile.activities.strategies.UserSettingsStrategy;
import com.play4u.mobile.domain.MusicJockey;
import com.play4u.mobile.services.MusicJockeySettingsCreateService;
import com.play4u.mobile.services.MusicJockeySettingsUpdateService;
import com.play4u.mobile.services.UserSettingsService;
import com.play4u.mobile.util.DirtyEditText;

public class MusicJockeySettingsActivity extends UserSettingsActivity {
    protected DirtyEditText stageNameTextInput;


    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stageNameTextInput=new DirtyEditText((EditText)findViewById(R.id.stage_name));
    }

    public DirtyEditText getStageNameTextInput(){
        return stageNameTextInput;
    }


    public UserSettingsService createService(){
        return new MusicJockeySettingsCreateService(this,MusicJockey.singleton());
    }

    public UserSettingsService updateService(){
        return new MusicJockeySettingsUpdateService(this, MusicJockey.singleton());
    }

    public UserSettingsStrategy createStrategy(){
        return new MusicJockeySettingsCreateStrategy(this);
    }

    public UserSettingsStrategy updateStrategy(){
        return new MusicJockeySettingsUpdateStrategy(this);
    }
}
