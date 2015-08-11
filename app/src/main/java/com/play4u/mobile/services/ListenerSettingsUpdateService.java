package com.play4u.mobile.services;

import android.content.Context;

import com.play4u.mobile.domain.Listener;
import com.play4u.mobile.services.tasks.AbstractServiceTask;
import com.play4u.mobile.services.tasks.ListenerInfoUpdateServiceTask;


/**
 * Proxy to enable service-level(i.e. RESTful) accessor for a listener
 * Created by ykeyser on 8/6/15.
 */
public class ListenerSettingsUpdateService extends ListenerSettingsService{
    public ListenerSettingsUpdateService(final Context ctx, final Listener listener){
        super(ctx, listener);
    }

    public AbstractServiceTask createInfoServiceTask(){
        return new ListenerInfoUpdateServiceTask(getContext(),getUser().getUserId());
    }
}