package com.play4u.mobile.util;

import android.util.Log;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONObject;

/**
 * Created by ykeyser on 8/8/15.
 */
public class EmptyJSONObject {
    private static volatile JSONObject helper;

    public static JSONObject singleton(){
        JSONObject result = helper;
        if (result == null) {
            synchronized(EmptyJSONObject.class) {
                result = helper;
                if (result == null) {
                    try {
                        helper = result = new JSONObject();
                    }
                    catch (Exception ex){
                        Log.e("EmptyJSONObject", ExceptionUtils.getStackTrace(ex));
                    }
                }
            }
        }
        return result;
    }
}
