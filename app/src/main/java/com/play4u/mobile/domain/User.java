package com.play4u.mobile.domain;

import android.content.SharedPreferences;
import android.util.Log;

import com.play4u.mobile.decorators.DisplayableSharedPreferences;
import com.play4u.mobile.singletons.EmptyJSONObject;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONObject;

/**
 * Adapter for user info
 * Created by ykeyser on 8/4/15.
 */
public class User {
    protected SharedPreferences prefs;
    protected final String USER_ID_KEY="user.id";
    protected final String EMAIL_KEY="user_email";
    protected final String LONG="longitude";
    protected final String LAT="latitude";
    public static final String USER_TYPE_KEY="user.type";
    public static final String USER_TYPE="unknown";
    protected static volatile User helper;
    protected static final String LOG_TAG="User";

    /*
    prefs: user's shared preferences. Only required on first instantiation.
     */
    public static User singleton(final SharedPreferences prefs){
        User result = helper;
        if (result == null) {
            synchronized(User.class) {
                result = helper;
                if (result == null) {
                    try {
                        helper = result = new User(prefs);
                    }
                    catch (Exception ex){
                        Log.e(LOG_TAG, ExceptionUtils.getStackTrace(ex));
                    }
                }
            }
        }
        return result;
    }

    public static User singleton(){
        return User.singleton(null);
    }

    public void destroy(){
        User.helper=null;
    }


    protected User(final SharedPreferences prefs) {
        if(prefs == null){
            throw new IllegalArgumentException("Shared preferences is blank");
        }else{
            final String prefsStr=new DisplayableSharedPreferences(prefs).toString();
            Log.i(LOG_TAG,"Prefs: "+prefsStr);
        }

        this.prefs = prefs;
    }

    public JSONObject toJSON(){
        try {
            return new JSONObject()
                    .put(EMAIL_KEY, getEmail())
                    .put(LONG,getLongitude())
                    .put(LAT,getLatitude());
        }
        catch (Exception ex){
            Log.e(LOG_TAG, ExceptionUtils.getStackTrace(ex));
            return EmptyJSONObject.singleton();
        }
    }

    public String toString(){
        return toJSON().toString();
    }

    /*
    Setters
     */

    public User setLatitude(final float latitude){
        prefs.edit().putFloat(LAT, latitude);
        return this;
    }

    public User setLongitude(final float longitude){
        prefs.edit().putFloat(LONG, longitude);
        return this;
    }

    public User setEmail(final String emailValue){
        prefs.edit().putString(EMAIL_KEY, emailValue);
        return this;
    }

    public User setUserId(final Integer userId){
        prefs.edit().putInt(USER_ID_KEY,userId);
        return this;
    }

    /*
    Getters
     */
    public Integer getUserId(){return this.prefs.getInt(USER_ID_KEY,-1);}

    public String getEmail(){
        return this.prefs.getString(EMAIL_KEY,"");
    }

    public Float getLatitude(){
        return prefs.getFloat(LAT, 0.0f);
    }

    public Float getLongitude(){
        return prefs.getFloat(LONG, 0.0f);
    }
}
