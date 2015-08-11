package com.play4u.mobile;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.play4u.mobile.decorators.DirtyEditText;
import com.play4u.mobile.domain.Listener;
import com.play4u.mobile.services.proxies.ListenerSettingsCreateService;
import com.play4u.mobile.services.proxies.ListenerSettingsUpdateService;
import com.play4u.mobile.strategies.ActivityStrategy;
import com.play4u.mobile.strategies.ListenerSettingsUpdateStrategy;

public class ListenerSettingsActivity extends Activity {
    protected ListenerSettingsUpdateService listenerSettingsUpdateService;
    protected ListenerSettingsCreateService listenerSettingsCreateService;
    protected DirtyEditText firstNameTextInput;
    protected DirtyEditText emailTextInput;

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.listenerSettingsUpdateService=new ListenerSettingsUpdateService(getApplicationContext(),getListener());
        this.listenerSettingsCreateService=new ListenerSettingsCreateService(getApplicationContext(),getListener());
        setContentView(R.layout.activity_listener_settings);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowTitleEnabled(false);
        firstNameTextInput=new DirtyEditText((EditText)findViewById(R.id.first_name));
        emailTextInput=new DirtyEditText((EditText)findViewById(R.id.email));
    }

    public DirtyEditText getEmailTextInput(){
        return emailTextInput;
    }

    public DirtyEditText getFirstNameTextInput(){
        return firstNameTextInput;
    }

    public ListenerSettingsUpdateService getListenerSettingsUpdateService(){
        return listenerSettingsUpdateService;
    }

    public ListenerSettingsCreateService getListenerSettingsCreateService(){
        return listenerSettingsCreateService;
    }

    public Listener getListener(){
        return Listener.singleton(getPreferences(MODE_PRIVATE));
    }

    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_listener_settings, menu);
        return true;
    }


    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case android.R.id.home:
                final ActivityStrategy strategy=new ListenerSettingsUpdateStrategy(this);
                strategy.doStrategy();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
