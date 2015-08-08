package com.play4u.mobile.domain;

import android.content.SharedPreferences;
import android.util.Log;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONException;
import org.json.JSONStringer;

/**
 * Created by ykeyser on 8/6/15.
 */
public class Listener {
    protected SharedPreferences prefs;
    public static final String FIRST_NAME="fname";
    public static final String EMAIL="email";

    public Listener(final SharedPreferences prefs){
        this.prefs=prefs;
    }

    /*
    Setters
     */
    public Listener setFirstName(final String firstName){
        prefs.edit().putString(FIRST_NAME,firstName);
        return this;
    }

    public Listener setEmail(final String email){
        prefs.edit().putString(EMAIL,email);
        return this;
    }

    /*
    Getters
     */
    public String getFirstName(){
        return prefs.getString(FIRST_NAME,"");
    }

    public String getEmail(){
        return prefs.getString(EMAIL,"");
    }

    /*
    Utils
     */

    public String toString(){
        try {
            return new JSONStringer().object().key("first_name").value(getFirstName()).key("email").value(getEmail())
                    .endObject().toString();
        }
        catch(final JSONException ex){
            Log.e("Listener", ExceptionUtils.getStackTrace(ex));
        }

        return "";
    }
}
