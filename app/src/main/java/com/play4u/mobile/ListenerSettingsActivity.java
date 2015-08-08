package com.play4u.mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.play4u.mobile.domain.Listener;
import com.play4u.mobile.services.adapters.ListenerSettingsService;

public class ListenerSettingsActivity extends Activity {
    protected ListenerSettingsService listenerSettings;
    protected Listener listener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listener_settings);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowTitleEnabled(false);
        this.listener=new Listener(getPreferences(MODE_PRIVATE));
        this.listenerSettings=new ListenerSettingsService(getApplicationContext(),this.listener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listener_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case android.R.id.home:
                Log.i("ListenerSettings","Ping: "+this.listenerSettings.getFirstName());
                final Intent intent = new Intent(this, ListenerActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
