package com.play4u.mobile.services.tasks;

import android.content.Context;

/**
 * Created by ykeyser on 8/11/15.
 */
public class MusicJockeyInfoCreateServiceTask extends UserInfoCreateServiceTask implements MusicJockeyInfoServiceTask {
    public MusicJockeyInfoCreateServiceTask(final Context ctx){
        super(ctx);
    }

    public String getRoute(){
        return ROUTE;
    }
}
