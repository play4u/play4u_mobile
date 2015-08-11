package com.play4u.mobile.services;

import android.content.Context;

import com.play4u.mobile.domain.MusicJockey;
import com.play4u.mobile.services.tasks.AbstractServiceTask;
import com.play4u.mobile.services.tasks.MusicJockeyInfoUpdateServiceTask;


/**
 * Proxy to enable service-level(i.e. RESTful) accessor for a listener
 * Created by ykeyser on 8/6/15.
 */
public class MusicJockeySettingsUpdateService extends MusicJockeySettingsService{
    public MusicJockeySettingsUpdateService(final Context ctx, final MusicJockey user){
        super(ctx, user);
    }

    public AbstractServiceTask createInfoServiceTask(){
        return new MusicJockeyInfoUpdateServiceTask(getContext(),getUser().getUserId());
    }
}