package com.play4u.mobile.decorators;

import android.content.SharedPreferences;
import android.util.Log;

import com.play4u.mobile.singletons.EmptyJSONObject;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by ykeyser on 8/10/15.
 */
public class DisplayableSharedPreferences {
    protected SharedPreferences prefs;
    protected static final String LOG_TAG="DisplayableSharedPrefs";

    public DisplayableSharedPreferences(final SharedPreferences prefs){
        this.prefs=prefs;
    }

    public JSONObject toJSON(){
        if(prefs==null) {
            return EmptyJSONObject.singleton();
        }

        final Map<String,?> nameValuePairs=prefs.getAll();
        final JSONObject jsonObj=new JSONObject();
        final Iterator<String> keysIter=nameValuePairs.keySet().iterator();

        while (keysIter.hasNext()){
            final String key = keysIter.next();
            final String value = nameValuePairs.get(key).toString();

            try {
                jsonObj.put(key, value);
            }
            catch (JSONException ex){
                Log.e(LOG_TAG, ExceptionUtils.getStackTrace(ex));
            }
        }

        return jsonObj;
    }

    public String toString(){
        return toJSON().toString();
    }
}
