package com.play4u.mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SelectUserTypeActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_user_type_activity);
    }

    public void selectMusicJockey(final View view){
        Log.i("SelectUserTypeActivity","Selected music jockey");
    }

    public void selectListener(final View view){
        Log.i("SelectUserTypeActivity","Selected listener");
        final Intent intent = new Intent(this, ListenerSettingsActivity.class);
        startActivity(intent);
    }
}
