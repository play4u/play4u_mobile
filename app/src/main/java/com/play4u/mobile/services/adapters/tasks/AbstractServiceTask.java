package com.play4u.mobile.services.adapters.tasks;

import android.content.Context;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

/**
 * Created by ykeyser on 8/8/15.
 */
public abstract class AbstractServiceTask extends AsyncTask<NameValuePair, Void, JSONObject> {
    protected final String USER_AGENT="Android";
    protected final AndroidHttpClient httpClient;
    protected final Context ctx;
    protected String route;

    protected AbstractServiceTask(final Context ctx) {
        this.ctx=ctx;
        httpClient = AndroidHttpClient.newInstance(USER_AGENT);
    }

    public String getRoute(){
        return route;
    }

    public void close(){
        httpClient.close();
    }
}
