package com.play4u.mobile.services.adapters.tasks;

import android.content.Context;
import android.util.Log;

import com.play4u.mobile.R;
import com.play4u.mobile.services.adapters.tasks.facades.HttpResponseReader;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;

/**
 * Created by ykeyser on 8/8/15.
 */
public class PingServiceTask extends AbstractServiceTask {
    protected final String route;

    public PingServiceTask(final Context ctx) {
        super(ctx);
        route=ctx.getString(R.string.ping_base_route);
    }

    public String doInBackground(final NameValuePair... params){
        try {
            final HttpGet httpGet = new HttpGet(ctx.getString(R.string.web_server_base_url) + route);
            final HttpResponse response = this.httpClient.execute(httpGet);
            return new HttpResponseReader(response).read();
        }
        catch(Exception ex){
            Log.e("PingTask", ExceptionUtils.getStackTrace(ex));
            return "";
        }
        finally{
            httpClient.close();
        }
    }
}
