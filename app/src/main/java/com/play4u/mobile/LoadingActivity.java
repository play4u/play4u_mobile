package com.play4u.mobile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;

import com.play4u.mobile.facades.BitMapRescaleFacade;
import com.play4u.mobile.pref.UserPreferences;

public class LoadingActivity extends Activity {
    protected UserPreferences userPreferences;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        final View view = findViewById(R.id.loading_layout);
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.loading_background);

        view.setBackground(new BitmapDrawable(getResources(),
                new BitMapRescaleFacade(getWindowManager(),bitmap).rescaleImage()));

        userPreferences=new UserPreferences(getPreferences(MODE_PRIVATE));
    }

    public void onResume(){
        super.onResume();

        if (userPreferences.isListener()){

        }
        else if (userPreferences.isMusicJockey()){

        }
        else {
            final Intent intent = new Intent(this, SelectUserTypeActivity.class);
            startActivity(intent);
        }
    }
}
