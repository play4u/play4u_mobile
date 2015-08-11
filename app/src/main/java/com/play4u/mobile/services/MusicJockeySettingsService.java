package com.play4u.mobile.services;

import android.content.Context;

import com.play4u.mobile.domain.MusicJockey;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;


/**
 * Proxy to enable service-level(i.e. RESTful) accessor for a listener
 * Created by ykeyser on 8/6/15.
 */
public abstract class MusicJockeySettingsService extends UserSettingsService {
    protected final List<BasicNameValuePair> httpParams=new ArrayList<BasicNameValuePair>();

    public MusicJockeySettingsService(final Context ctx, final MusicJockey user){
        super(ctx, user);
    }

    public MusicJockey getUser(){
        return (MusicJockey)super.getUser();
    }

    /*
    Setters
     */
    public MusicJockeySettingsService setStageName(final String stageName){
        getUser().setStageName(stageName);
        setShouldSend(true);
        addHttpNameValuePair("stage_name",stageName);
        return this;
    }
}