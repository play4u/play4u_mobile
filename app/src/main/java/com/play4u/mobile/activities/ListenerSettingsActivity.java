package com.play4u.mobile.activities;

import android.os.Bundle;
import android.widget.EditText;

import com.play4u.mobile.R;
import com.play4u.mobile.activities.strategies.ListenerSettingsCreateStrategy;
import com.play4u.mobile.activities.strategies.ListenerSettingsUpdateStrategy;
import com.play4u.mobile.activities.strategies.UserSettingsStrategy;
import com.play4u.mobile.domain.Listener;
import com.play4u.mobile.services.ListenerSettingsCreateService;
import com.play4u.mobile.services.ListenerSettingsUpdateService;
import com.play4u.mobile.util.DirtyEditText;

public class ListenerSettingsActivity extends UserSettingsActivity {
    protected DirtyEditText firstNameTextInput;

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firstNameTextInput=new DirtyEditText((EditText)findViewById(R.id.first_name));
    }

    public DirtyEditText getFirstNameTextInput(){
        return firstNameTextInput;
    }

    public UserSettingsStrategy createStrategy(){
        return new ListenerSettingsCreateStrategy(this,new ListenerSettingsCreateService(this, Listener.singleton()));
    }
    public UserSettingsStrategy updateStrategy(){
        return new ListenerSettingsUpdateStrategy(this,new ListenerSettingsUpdateService(this,Listener.singleton()));
    }
}
