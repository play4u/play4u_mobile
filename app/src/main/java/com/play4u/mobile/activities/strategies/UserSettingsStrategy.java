package com.play4u.mobile.activities.strategies;

import com.play4u.mobile.activities.UserSettingsActivity;
import com.play4u.mobile.services.UserSettingsService;

/**
 * Created by ykeyser on 8/8/15.
 */
public abstract class UserSettingsStrategy {
    protected UserSettingsActivity activity;
    protected UserSettingsService service;

    public UserSettingsStrategy(final UserSettingsActivity activity){
        this.activity=activity;
    }

    public UserSettingsActivity getActivity(){
        return activity;
    }

    protected void setService(final UserSettingsService service){
        this.service=service;
    }

    protected UserSettingsService getService(){
        return service;
    }

    public abstract void doStrategy();
}
