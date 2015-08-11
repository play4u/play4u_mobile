package com.play4u.mobile.services.proxies;

import android.content.Context;

import com.play4u.mobile.domain.Listener;
import com.play4u.mobile.services.adapters.exceptions.ServiceCommitException;
import com.play4u.mobile.services.proxies.tasks.AbstractServiceTask;
import com.play4u.mobile.services.proxies.tasks.ListenerInfoServiceTask;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Proxy to enable service-level(i.e. RESTful) accessor for a listener
 * Created by ykeyser on 8/6/15.
 */
public abstract class ListenerSettingsService {
    protected final Listener listener;
    protected final Context ctx;
    protected final List<BasicNameValuePair> httpParams=new ArrayList<BasicNameValuePair>();
    protected boolean shouldSend;

    public ListenerSettingsService(final Context ctx, final Listener listener){
        this.listener=listener;
        this.ctx=ctx;
    }

    /*
    Getters
     */
    public Listener getListener(){
        return listener;
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

    public ListenerSettingsService setEmail(final String email){
        listener.setEmail(email);
        shouldSend=true;
        httpParams.add(new BasicNameValuePair("email", email));
        return this;
    }

    public ListenerSettingsService setLongitude(final Float longitude){
        listener.setLongitude(longitude);
        shouldSend=true;
        httpParams.add(new BasicNameValuePair("longitude", longitude.toString()));
        return this;
    }

    public ListenerSettingsService setLatitude(final Float latitude){
        listener.setLatitude(latitude);
        shouldSend=true;
        httpParams.add(new BasicNameValuePair("latitude", latitude.toString()));
        return this;
    }

    public abstract ListenerInfoServiceTask createInfoServiceTask(final Context ctx, final Listener listener);

    public JSONObject send() throws Exception{
        if(shouldSend) {
            final AbstractServiceTask task = createInfoServiceTask(ctx,listener);
            NameValuePair[] nameValuePairsArray = new NameValuePair[httpParams.size()];
            httpParams.toArray(nameValuePairsArray);
            task.execute(nameValuePairsArray);
            shouldSend=false;
            final JSONObject respJSON=task.get();
            task.close();
            return respJSON;
        }
        else {
            throw new ServiceCommitException("No new data to send");
        }
    }
}