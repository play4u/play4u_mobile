package com.play4u.mobile.services;

import android.content.Context;

import com.play4u.mobile.domain.Listener;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;


/**
 * Proxy to enable service-level(i.e. RESTful) accessor for a listener
 * Created by ykeyser on 8/6/15.
 */
public abstract class ListenerSettingsService extends UserSettingsService {
    protected final List<BasicNameValuePair> httpParams=new ArrayList<BasicNameValuePair>();
    protected boolean shouldSend;
    protected Listener listener;

    public ListenerSettingsService(final Context ctx, final Listener listener){
        super(ctx,listener);
        this.listener=listener;
    }

    /*
    Setters
     */
    public ListenerSettingsService setFirstName(final String firstName){
        listener.setFirstName(firstName);
        shouldSend=true;
        httpParams.add(new BasicNameValuePair("first_name", firstName));
        return this;
    }
}