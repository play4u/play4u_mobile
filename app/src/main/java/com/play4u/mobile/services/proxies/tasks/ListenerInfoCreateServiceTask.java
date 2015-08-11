package com.play4u.mobile.services.proxies.tasks;

import android.content.Context;
import android.util.Log;

import com.play4u.mobile.R;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;

import java.util.Arrays;

/**
 * Created by ykeyser on 8/8/15.
 */
public class ListenerInfoCreateServiceTask extends ListenerInfoServiceTask {
    protected static final String LOG_TAG="ListenerInfoCreateTask";

    public ListenerInfoCreateServiceTask(final Context ctx) {
        super(ctx);
    }

    public HttpUriRequest createHttpUriRequest(final NameValuePair... params) throws Exception{
        final String uri=ctx.getString(R.string.web_server_base_url)+route;
        Log.i(LOG_TAG, "[ListenerInfoCreateServiceTask] URI: " + uri);
        Log.i(LOG_TAG, "[ListenerInfoCreateServiceTask] Params: "+Arrays.asList(params));
        final HttpPost httpPost=new HttpPost(uri);
        httpPost.setEntity(new UrlEncodedFormEntity(Arrays.asList(params)));
        return httpPost;
    }
}
