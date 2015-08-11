package com.play4u.mobile.services;

import android.content.Context;

import com.play4u.mobile.domain.MusicJockey;
import com.play4u.mobile.services.tasks.AbstractServiceTask;
import com.play4u.mobile.services.tasks.MusicJockeyInfoCreateServiceTask;


/**
 * Proxy to enable service-level(i.e. RESTful) accessor for a listener
 * Created by ykeyser on 8/6/15.
 */
public class MusicJockeySettingsCreateService extends MusicJockeySettingsService {
   public MusicJockeySettingsCreateService(final Context ctx, final MusicJockey user){
       super(ctx,user);
   }

    public AbstractServiceTask createInfoServiceTask(){
        return new MusicJockeyInfoCreateServiceTask(getContext());
    }
}