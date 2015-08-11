package com.play4u.mobile.services.proxies.tasks;

import android.content.Context;
import android.util.Log;

import com.play4u.mobile.R;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;

import java.util.Arrays;

/**
 * Created by ykeyser on 8/8/15.
 */
public class ListenerInfoUpdateServiceTask extends ListenerInfoServiceTask {
    protected static final String ID_PARAM="id";
    protected Integer userId;
    protected static final String LOG_TAG="ListenerInfoUpdate";

    public ListenerInfoUpdateServiceTask(final Integer userId, final Context ctx) {
        super(ctx);

        if(userId == null){
            throw new IllegalStateException("User id is blank");
        }

        this.userId=userId;
    }

    public HttpUriRequest createHttpUriRequest(final NameValuePair... params) throws Exception{
        final String uri=ctx.getString(R.string.web_server_base_url)+route+'/'+userId;
        Log.i(LOG_TAG,"[ListenerInfoUpdateServiceTask] URI: "+uri);
        Log.i(LOG_TAG, "[ListenerInfoUpdateServiceTask] Params: "+Arrays.asList(params));
        final HttpPut httpPut=new HttpPut(uri);
        httpPut.setEntity(new UrlEncodedFormEntity(Arrays.asList(params)));
        return httpPut;
    }
}
