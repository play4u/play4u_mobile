package com.play4u.mobile.services.tasks;

import android.content.Context;
import android.net.http.AndroidHttpClient;
import android.util.Log;

import com.play4u.mobile.util.EmptyJSONObject;
import com.play4u.mobile.util.HttpResponseReader;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpUriRequest;
import org.json.JSONObject;

/**
 * Created by ykeyser on 8/10/15.
 */
public abstract class SendTask extends AbstractServiceTask  {
    protected static final String LOG_TAG="SendTask";
    protected final AndroidHttpClient httpClient;

    protected SendTask(final Context ctx) {
        super(ctx);
        httpClient = AndroidHttpClient.newInstance(USER_AGENT);
    }

    public void close(){
        httpClient.close();
    }

    public abstract String getRoute();
    public abstract HttpUriRequest createHttpUriRequest(final NameValuePair... params) throws Exception;

    public JSONObject doInBackground(final NameValuePair... params){
        try{

            final HttpResponse response = httpClient.execute(createHttpUriRequest(params));
            final String respStr = new HttpResponseReader(response).read();
            Log.i(LOG_TAG, "Server resp: " + respStr);
            return new JSONObject(respStr);
        }
        catch (Exception ex){
            Log.e(LOG_TAG, ExceptionUtils.getStackTrace(ex));
            return EmptyJSONObject.singleton();
        }
    }
}
