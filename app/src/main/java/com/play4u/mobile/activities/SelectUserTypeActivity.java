package com.play4u.mobile.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.play4u.mobile.R;
import com.play4u.mobile.domain.Listener;
import com.play4u.mobile.domain.MusicJockey;
import com.play4u.mobile.domain.User;

public class SelectUserTypeActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_user_type_activity);
    }

    public void selectMusicJockey(final View view){
        Log.i("SelectUserTypeActivity", "Selected music jockey");
        User.singleton().destroy();
        MusicJockey.singleton(getPreferences(MODE_PRIVATE));
        final Intent intent=new Intent(this,MusicJockeyActivity.class);
        startActivity(intent);
    }

    public void selectListener(final View view){
        Log.i("SelectUserTypeActivity","Selected listener");
        User.singleton().destroy();
        Listener.singleton(getPreferences(MODE_PRIVATE));
        final Intent intent = new Intent(this, ListenerSettingsActivity.class);
        startActivity(intent);
    }
}
