package com.play4u.mobile.services.tasks;

import android.content.Context;

/**
 * Created by ykeyser on 8/11/15.
 */
public class MusicJockeyInfoUpdateServiceTask extends UserInfoUpdateServiceTask implements MusicJockeyInfoServiceTask {
    public MusicJockeyInfoUpdateServiceTask(final Context ctx, final Integer userId){
        super(ctx,userId);
    }

    public String getRoute(){
        return ROUTE;
    }
}
