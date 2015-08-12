package com.play4u.mobile.activities;

import android.os.Bundle;
import android.widget.EditText;

import com.play4u.mobile.R;
import com.play4u.mobile.activities.strategies.MusicJockeySettingsStrategy;
import com.play4u.mobile.activities.strategies.UserSettingsStrategy;
import com.play4u.mobile.domain.MusicJockey;
import com.play4u.mobile.services.MusicJockeySettingsCreateService;
import com.play4u.mobile.services.MusicJockeySettingsUpdateService;
import com.play4u.mobile.util.DirtyEditText;

public class MusicJockeySettingsActivity extends UserSettingsActivity {
    protected DirtyEditText stageNameTextInput;

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(findViewById(R.id.stage_name)==null){
            throw new NullPointerException("Stage-name edit-text is null. Id: "+R.id.stage_name);
        }

        stageNameTextInput=new DirtyEditText((EditText)findViewById(R.id.stage_name));
    }

    protected void initializeLayout(){
        setContentView(R.layout.activity_music_jockey_settings);
    }

    public DirtyEditText getStageNameTextInput(){
        return stageNameTextInput;
    }

    public UserSettingsStrategy createStrategy(){
        return new MusicJockeySettingsStrategy(this,new MusicJockeySettingsCreateService(this,MusicJockey.singleton()));
    }

    public UserSettingsStrategy updateStrategy(){
        return new MusicJockeySettingsStrategy(this,new MusicJockeySettingsUpdateService(this, MusicJockey.singleton()));
    }
}
