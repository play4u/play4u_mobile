package com.play4u.mobile.services.tasks;

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
public abstract class UserInfoUpdateServiceTask extends SendTask {
    protected static final String ID_PARAM="id";
    protected Integer userId;
    protected static final String LOG_TAG="UserInfoUpdate";

    public UserInfoUpdateServiceTask(final Context ctx, final Integer userId) {
        super(ctx);

        if(userId == null){
            throw new IllegalStateException("User id is blank");
        }

        this.userId=userId;
    }

    public Integer getUserId(){
        return userId;
    }

    public HttpUriRequest createHttpUriRequest(final NameValuePair... params) throws Exception{
        final String uri=getContext().getString(R.string.web_server_base_url)+ getRoute();
        Log.i(LOG_TAG,"[ListenerInfoUpdateServiceTask] URI: "+uri);
        Log.i(LOG_TAG, "[ListenerInfoUpdateServiceTask] Params: "+Arrays.asList(params));
        final HttpPut httpPut=new HttpPut(uri);
        httpPut.setEntity(new UrlEncodedFormEntity(Arrays.asList(params)));
        return httpPut;
    }
}
