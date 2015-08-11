package com.play4u.mobile.activities.strategies;

import android.util.Log;

import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * Created by ykeyser on 8/11/15.
 */
public class UserSettingsContext {
    protected UserSettingsStrategy strategy;
    private static volatile UserSettingsContext helper;
    protected static final String LOG_TAG="UserSettingsContext";

    protected UserSettingsContext(){}

    public static UserSettingsContext singleton(){
        UserSettingsContext result = helper;
        if (result == null) {
            synchronized(UserSettingsContext.class) {
                result = helper;
                if (result == null) {
                    try {
                        helper = result = new UserSettingsContext();
                    }
                    catch (Exception ex){
                        Log.e(LOG_TAG, ExceptionUtils.getStackTrace(ex));
                    }
                }
            }
        }
        return result;
    }

    public UserSettingsStrategy getStrategy(){
        return strategy;
    }

    public UserSettingsContext setStrategy(final UserSettingsStrategy strategy){
        this.strategy=strategy;
        return this;
    }

    public void doStrategy(){
        if(strategy instanceof UserSettingsStrategy) {
            strategy.doStrategy();
        }
    }
}
