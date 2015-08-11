package com.play4u.mobile.strategies;

import com.play4u.mobile.ListenerSettingsActivity;
import com.play4u.mobile.services.proxies.ListenerSettingsService;

/**
 * Created by ykeyser on 8/8/15.
 */
public class ListenerSettingsUpdateStrategy extends ListenerSettingsStrategy{
    public ListenerSettingsUpdateStrategy(final ListenerSettingsActivity activity){
       super(activity);
    }

    public ListenerSettingsService createService(){
        return activity.getListenerSettingsUpdateService();
    }
}
