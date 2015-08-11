package com.play4u.mobile.services.proxies;

import android.content.Context;

import com.play4u.mobile.domain.Listener;
import com.play4u.mobile.services.proxies.tasks.ListenerInfoCreateServiceTask;
import com.play4u.mobile.services.proxies.tasks.ListenerInfoServiceTask;


/**
 * Proxy to enable service-level(i.e. RESTful) accessor for a listener
 * Created by ykeyser on 8/6/15.
 */
public class ListenerSettingsCreateService extends ListenerSettingsService {
   public ListenerSettingsCreateService(final Context ctx, final Listener listener){
       super(ctx,listener);
   }

    public ListenerInfoServiceTask createInfoServiceTask(final Context ctx, final Listener listener){
        return new ListenerInfoCreateServiceTask(ctx);
    }
}