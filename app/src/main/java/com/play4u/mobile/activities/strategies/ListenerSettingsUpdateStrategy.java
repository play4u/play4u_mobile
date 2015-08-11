package com.play4u.mobile.activities.strategies;

import com.play4u.mobile.activities.ListenerSettingsActivity;
import com.play4u.mobile.services.ListenerSettingsService;

/**
 * Created by ykeyser on 8/8/15.
 */
public class ListenerSettingsUpdateStrategy extends UserSettingsUpdateStrategy{
    public ListenerSettingsUpdateStrategy(final ListenerSettingsActivity activity, final ListenerSettingsService service){
        super(activity);
        super.setService(service);
    }
}
