package com.play4u.mobile.util;

import org.apache.http.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ykeyser on 8/8/15.
 */
public class HttpResponseReader {
    protected HttpResponse response;

    public HttpResponseReader(final HttpResponse response){
        this.response=response;
    }

    public String read() throws IOException{
        final StringBuilder respBuilder = new StringBuilder();
        final BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        String line="";
        while ((line = rd.readLine()) != null) {
            respBuilder.append(line+'\n');
        }

        return respBuilder.toString();
    }
}
