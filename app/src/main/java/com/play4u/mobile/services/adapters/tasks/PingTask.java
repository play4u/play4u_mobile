package com.play4u.mobile.services.adapters.tasks;

import android.content.Context;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

import com.play4u.mobile.R;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by ykeyser on 8/8/15.
 */
public class PingTask extends AsyncTask<Void, Void, String> {
    protected final String USER_AGENT="Android";
    protected final AndroidHttpClient httpClient;
    protected final Context ctx;
    protected final String route;

    public PingTask(final Context ctx) {
        this.ctx=ctx;
        httpClient = AndroidHttpClient.newInstance(USER_AGENT);
        route=ctx.getString(R.string.ping_route);
    }

    public String doInBackground(Void... params){
        Log.i("ListenerSettings", "Base url: " + ctx.getString(R.string.web_server_base_url));
        final HttpGet httpGet = new HttpGet(ctx.getString(R.string.web_server_base_url) + route);

        try {
            final HttpResponse response = this.httpClient.execute(httpGet);
            final InputStreamReader isr=new InputStreamReader(response.getEntity().getContent());
            final BufferedReader reader = new BufferedReader(isr);
            Log.i("ListenerSettings", "HTTP resp: " + reader.readLine());
            return response.toString();
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
