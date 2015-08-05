package com.play4u.mobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SelectUserTypeActivity extends AppCompatActivity {
    protected View selectMusicJockeyButton, selectListenerButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_user_type_activity);
        selectListenerButton=findViewById(R.id.select_listener_button);
        selectMusicJockeyButton=findViewById(R.id.select_music_jockey_button);
    }

    public void selectMusicJockey(final View view){
        Log.i("SelectUserTypeActivity","Selected music jockey");
    }

    public void selectListener(final View view){
        Log.i("SelectUserTypeActivity","Selected listener");
    }
}
