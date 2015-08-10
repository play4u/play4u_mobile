package com.play4u.mobile.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by ykeyser on 8/10/15.
 */
public class PushNotificationService extends IntentService {
    protected static final String LOG_TAG="PushNotificationService";

    public PushNotificationService(){
        super(LOG_TAG);
    }

    protected void onHandleIntent(final Intent workIntent) {
        Log.i(LOG_TAG, "Task requested");

    }

    public void onCreate(){
        Log.i(LOG_TAG,"Service created. Standing by for requests...");
    }

}
