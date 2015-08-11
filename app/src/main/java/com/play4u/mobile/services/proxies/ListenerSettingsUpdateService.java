package com.play4u.mobile.services.proxies;

import android.content.Context;

import com.play4u.mobile.domain.Listener;
import com.play4u.mobile.services.proxies.tasks.ListenerInfoServiceTask;
import com.play4u.mobile.services.proxies.tasks.ListenerInfoUpdateServiceTask;


/**
 * Proxy to enable service-level(i.e. RESTful) accessor for a listener
 * Created by ykeyser on 8/6/15.
 */
public class ListenerSettingsUpdateService extends ListenerSettingsService{
    public ListenerSettingsUpdateService(final Context ctx, final Listener listener){
        super(ctx, listener);
    }

    public ListenerInfoServiceTask createInfoServiceTask(final Context ctx, final Listener listener){
        return new ListenerInfoUpdateServiceTask(listener.getUserId(),ctx);
    }
}