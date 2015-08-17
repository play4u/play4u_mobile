package com.play4u.mobile.services.singletons;

import android.util.Log;

import org.apache.commons.lang3.exception.ExceptionUtils;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;

/**
 * Created by ykeyser on 8/17/15.
 */
public class SpotifyApiClientSingleton {
    protected static volatile SpotifyService helper;
    protected static final String LOG_TAG="SpotifyApiClient";

    public static SpotifyService singleton(){
        SpotifyService result = helper;
        if (result == null) {
            synchronized(SpotifyApiClientSingleton.class) {
                result = helper;

                if (result == null) {
                    try {
                        final SpotifyApi api = new SpotifyApi();
                        helper = result = api.getService();
                    }
                    catch (Throwable ex){
                        Log.e(LOG_TAG, ExceptionUtils.getStackTrace(ex));
                    }
                }
            }
        }
        return result;
    }
}
