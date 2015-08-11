package com.play4u.mobile.domain;

import android.content.SharedPreferences;
import android.util.Log;

import com.play4u.mobile.singletons.EmptyJSONObject;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONObject;

/**
 * Created by ykeyser on 8/9/15.
 */
public class MusicJockey extends User {
    protected final String MUSIC_JOCKEY_STAGE_NAME_KEY="mj.stage_name";
    public static final String USER_TYPE_VALUE="MusicJockey";
    public static final String LOG_TAG="MusicJockey";

    protected MusicJockey(final SharedPreferences prefs){
        super(prefs);
    }

    public static MusicJockey singleton(final SharedPreferences prefs){
        User result = helper;
        if (result == null) {
            synchronized(MusicJockey.class) {
                result = helper;
                if (result == null) {
                    try {
                        helper = result = new MusicJockey(prefs);
                    }
                    catch (Exception ex){
                        Log.e(LOG_TAG, ExceptionUtils.getStackTrace(ex));
                    }
                }
            }
        }
        return (MusicJockey)result;
    }

    public static MusicJockey singleton(){
        return MusicJockey.singleton(null);
    }

    /*
    Setters
     */
    public User setMusicJockeyStageName(final String stageNameValue){
        prefs.edit().putString(MUSIC_JOCKEY_STAGE_NAME_KEY, stageNameValue);
        return this;
    }

    /*
    Getters
     */
    public String getMusicJockeyStageName(){
        return this.prefs.getString(MUSIC_JOCKEY_STAGE_NAME_KEY, "");
    }

    /*
    Utils
     */
    public JSONObject toJSON(){
        try {
            return super.toJSON().put(MUSIC_JOCKEY_STAGE_NAME_KEY, getMusicJockeyStageName());
        }
        catch (Exception ex){
            Log.e("MusicJockey", ExceptionUtils.getStackTrace(ex));
            return EmptyJSONObject.singleton();
        }
    }

    public String toString(){
        return toJSON().toString();
    }
}
