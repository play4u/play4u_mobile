package com.play4u.mobile.services.adapters.tasks;

import android.content.Context;
import android.util.Log;

import com.play4u.mobile.R;
import com.play4u.mobile.facades.EmptyJSONObject;
import com.play4u.mobile.services.adapters.tasks.facades.HttpResponseReader;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by ykeyser on 8/8/15.
 */
public class ListenerInfoUpdateServiceTask extends AbstractServiceTask {

    public ListenerInfoUpdateServiceTask(final Context ctx) {
        super(ctx);
        route=ctx.getString(R.string.listener_base_route);
    }

    public JSONObject doInBackground(final NameValuePair... params){
        try{
            final HttpPut httpPut=new HttpPut(ctx.getString(R.string.web_server_base_url)+route);
            httpPut.setEntity(new UrlEncodedFormEntity(Arrays.asList(params)));
            final HttpResponse response = httpClient.execute(httpPut);
            final String respStr = new HttpResponseReader(response).read();
            Log.i("ListenerInfoUpdate", "Server resp: "+respStr);
            return new JSONObject(respStr);
        }
        catch (Exception ex){
            Log.e("ListenerInfoUpdate", ExceptionUtils.getStackTrace(ex));
            return EmptyJSONObject.singleton();
        }
    }
}
