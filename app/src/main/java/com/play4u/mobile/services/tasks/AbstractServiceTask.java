package com.play4u.mobile.services.tasks;

import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

/**
 * Created by ykeyser on 8/8/15.
 */
public abstract class AbstractServiceTask extends AsyncTask<NameValuePair, Void, JSONObject> {
    protected final String USER_AGENT="Android";
    protected final Context ctx;

    protected AbstractServiceTask(final Context ctx) {
        this.ctx=ctx;
    }
    public void close(){}
    public Context getContext(){return ctx;}
}
