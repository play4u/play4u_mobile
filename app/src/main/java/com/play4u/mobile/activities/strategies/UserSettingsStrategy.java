package com.play4u.mobile.activities.strategies;

import com.play4u.mobile.activities.UserSettingsActivity;

/**
 * Created by ykeyser on 8/8/15.
 */
public abstract class UserSettingsStrategy {
    protected UserSettingsActivity activity;

    public UserSettingsStrategy(final UserSettingsActivity activity){
        this.activity=activity;
    }

    public UserSettingsActivity getActivity(){
        return activity;
    }

    public abstract void doStrategy();
}
