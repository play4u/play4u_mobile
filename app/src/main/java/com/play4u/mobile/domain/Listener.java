package com.play4u.mobile.domain;

import android.util.Log;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONException;
import org.json.JSONStringer;

/**
 * Created by ykeyser on 8/6/15.
 */
public class Listener {
    protected String firstName="",email="";

    /*
    Setters
     */
    public Listener setFirstName(final String firstName){
        this.firstName=firstName;
        return this;
    }

    public Listener setEmail(final String email){
        this.email=email;
        return this;
    }

    /*
    Getters
     */
    public String getFirstName(){
        return firstName;
    }

    public String getEmail(){
        return email;
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
