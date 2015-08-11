package com.play4u.mobile.services.tasks;

import android.content.Context;

import com.play4u.mobile.R;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

/**
 * Created by ykeyser on 8/8/15.
 */
public class PingServiceTask extends SendTask {
    protected static final String ROUTE="/ping";

    public PingServiceTask(final Context ctx) {
        super(ctx);
    }

    public String getRoute(){
        return ROUTE;
    }

    public HttpUriRequest createHttpUriRequest(final NameValuePair... params) throws Exception{
        return new HttpGet(ctx.getString(R.string.web_server_base_url) + getRoute());
    }
}
