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
    protected boolean shouldSend;
    protected MusicJockey user;

    public MusicJockeySettingsService(final Context ctx, final MusicJockey user){
        super(ctx, user);
        this.user=user;
    }

    /*
    Setters
     */
    public MusicJockeySettingsService setStageName(final String stageName){
        user.setStageName(stageName);
        shouldSend=true;
        httpParams.add(new BasicNameValuePair("stage_name", stageName));
        return this;
    }
}