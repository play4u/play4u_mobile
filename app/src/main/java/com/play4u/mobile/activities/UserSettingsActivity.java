package com.play4u.mobile.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.play4u.mobile.R;
import com.play4u.mobile.activities.strategies.UserSettingsContext;
import com.play4u.mobile.activities.strategies.UserSettingsStrategy;
import com.play4u.mobile.domain.User;
import com.play4u.mobile.util.DirtyEditText;

public abstract class UserSettingsActivity extends Activity {
    protected DirtyEditText emailTextInput;
    protected final UserSettingsContext userSettingsContext = UserSettingsContext.singleton();

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeLayout();
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowTitleEnabled(false);
        emailTextInput=new DirtyEditText((EditText)findViewById(R.id.email));
    }

    protected abstract void initializeLayout();

    public DirtyEditText getEmailTextInput(){
        return emailTextInput;
    }
    public User getUser(){
        return User.singleton();
    }

    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_listener_settings, menu);
        return true;
    }

    public abstract UserSettingsStrategy createStrategy();
    public abstract UserSettingsStrategy updateStrategy();

    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case android.R.id.home:
                if(userSettingsContext.getStrategy() == null){
                    userSettingsContext
                            .setStrategy(createStrategy())
                            .doStrategy();
                }else{
                    userSettingsContext
                            .setStrategy(updateStrategy())
                            .doStrategy();
                }

                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
