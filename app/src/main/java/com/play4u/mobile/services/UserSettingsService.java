package com.play4u.mobile.services;

import android.content.Context;

import com.play4u.mobile.domain.User;
import com.play4u.mobile.services.exceptions.ServiceCommitException;
import com.play4u.mobile.services.tasks.AbstractServiceTask;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Proxy to enable service-level(i.e. RESTful) accessor for a listener
 * Created by ykeyser on 8/6/15.
 */
public abstract class UserSettingsService {
    private final User user;
    private final Context ctx;
    private final List<BasicNameValuePair> httpParams=new ArrayList<BasicNameValuePair>();
    private boolean shouldSend;

    public UserSettingsService(final Context ctx, final User user){
        this.user=user;
        this.ctx=ctx;
    }

    /*
    Getters
     */
    public User getUser(){
        return user;
    }

    public Context getContext(){
        return ctx;
    }

    /*
    Setters
     */
    protected BasicNameValuePair addHttpNameValuePair(final String name, final String value){
        final BasicNameValuePair nameValuePair=new BasicNameValuePair(name, value);
        httpParams.add(nameValuePair);
        return nameValuePair;
    }

    protected void setShouldSend(final boolean shouldSend){
        this.shouldSend=shouldSend;
    }

    public UserSettingsService setEmail(final String email){
        getUser().setEmail(email);
        shouldSend=true;
        addHttpNameValuePair("email", email);
        return this;
    }

    public UserSettingsService setLongitude(final Float longitude){
        getUser().setLongitude(longitude);
        shouldSend=true;
        addHttpNameValuePair("longitude", longitude.toString());
        return this;
    }

    public UserSettingsService setLatitude(final Float latitude){
        getUser().setLatitude(latitude);
        shouldSend=true;
        addHttpNameValuePair("latitude", latitude.toString());
        return this;
    }

    public abstract AbstractServiceTask createInfoServiceTask();

    public JSONObject send() throws Exception{
        if(shouldSend) {
            final AbstractServiceTask task = createInfoServiceTask();
            NameValuePair[] nameValuePairsArray = new NameValuePair[httpParams.size()];
            httpParams.toArray(nameValuePairsArray);
            task.execute(nameValuePairsArray);
            shouldSend=false;
            httpParams.clear();
            //TODO: optimize in a thread
            final JSONObject respJSON=task.get();
            task.close();
            return respJSON;
        }
        else {
            throw new ServiceCommitException("No new data to send");
        }
    }
}