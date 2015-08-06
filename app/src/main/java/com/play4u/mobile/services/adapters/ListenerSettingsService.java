package com.play4u.mobile.services.adapters;

import com.play4u.mobile.domain.Listener;


/**
 * Proxy to enable service-level(i.e. RESTful) get/set of a listener
 * Created by ykeyser on 8/6/15.
 */
public class ListenerSettingsService {
    protected final Listener listener;


    public ListenerSettingsService(final Listener listener){
        this.listener=listener;
    }

    /*
    Getters
     */
    public Listener getListener(){
        return listener;
    }

    public String getFirstName(){
        return null;
    }

    public String getEmail(){
        return null;
    }

    /*
    Setters
     */
    public Listener setFirstName(final String firstName){
        listener.setFirstName(firstName);
        return listener;
    }

    public Listener setEmail(final String email){
        listener.setEmail(email);
        return listener;
    }
}
