package com.play4u.mobile.domain;

import android.content.SharedPreferences;
import android.util.Log;

import com.play4u.mobile.facades.EmptyJSONObject;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONObject;

/**
 * Created by ykeyser on 8/6/15.
 */
public class Listener extends User{
    protected SharedPreferences prefs;
    public static final String FIRST_NAME="fname";
    protected final String LISTENER_FIRST_NAME_KEY="listener.first_name";
    public static final String USER_TYPE_VALUE="Listener";
    public static final String LOG_TAG="Listener";

    protected Listener(final SharedPreferences prefs){
        super(prefs);
    }

    public static Listener singleton(final SharedPreferences prefs){
        User result = helper;
        if (result == null) {
            synchronized(Listener.class) {
                result = helper;
                if (result == null) {
                    try {
                        helper = result = new Listener(prefs);
                    }
                    catch (Exception ex){
                        Log.e(LOG_TAG, ExceptionUtils.getStackTrace(ex));
                    }
                }
            }
        }
        return (Listener)result;
    }

    public static Listener singleton(){
        return Listener.singleton(null);
    }

    /*
    Setters
     */
    public Listener setFirstName(final String firstName){
        prefs.edit().putString(FIRST_NAME,firstName);
        return this;
    }

    /*
    Getters
     */
    public String getFirstName(){
        return prefs.getString(FIRST_NAME, "");
    }


    /*
    Utils
     */

    public JSONObject toJSON(){
        try {
            return super.toJSON().put(FIRST_NAME, getFirstName());
        }
        catch (Exception ex){
            Log.e("Listener",ExceptionUtils.getStackTrace(ex));
            return EmptyJSONObject.singleton();
        }
    }

    public String toString(){
        return toJSON().toString();
    }
}
