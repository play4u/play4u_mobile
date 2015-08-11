package com.play4u.mobile.services.proxies.tasks;

import android.content.Context;
import android.util.Log;

import com.play4u.mobile.R;
import com.play4u.mobile.services.proxies.tasks.decorators.HttpResponseReader;
import com.play4u.mobile.singletons.EmptyJSONObject;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpUriRequest;
import org.json.JSONObject;

/**
 * Created by ykeyser on 8/10/15.
 */
public abstract class ListenerInfoServiceTask extends AbstractServiceTask {
    protected static final String LOG_TAG="ListenerInfoTask";

    protected ListenerInfoServiceTask(final Context ctx) {
        super(ctx);
        route=ctx.getString(R.string.listener_base_route);
    }

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
