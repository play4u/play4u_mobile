package com.play4u.mobile.pref;

import android.content.SharedPreferences;

/**
 * Adapter for user prefs
 * Created by ykeyser on 8/4/15.
 */
public class UserPreferences {
    protected final SharedPreferences prefs;
    protected final String EMAIL_KEY="user_email";
    protected final String MUSIC_JOCKEY_STAGE_NAME_KEY="mj.stage_name";
    protected final String LISTENER_FIRST_NAME_KEY="listener.first_name";
    protected final String USER_TYPE_KEY="user.type";
    protected final int LISTENER_TYPE=0;
    protected final int MUSIC_JOCKEY_TYPE=1;

    public UserPreferences(final SharedPreferences prefs){
        this.prefs=prefs;
    }

    public String getUserType(){
        return prefs.getString(USER_TYPE_KEY,"");
    }

    public  boolean isListener(){
        return prefs.getInt(USER_TYPE_KEY,-1)==LISTENER_TYPE;
    }

    public boolean isMusicJockey(){
        return prefs.getInt(USER_TYPE_KEY,-1)==MUSIC_JOCKEY_TYPE;
    }

    public UserPreferences setUserType(final int userTypeValue){
        prefs.edit().putInt(USER_TYPE_KEY,userTypeValue);
        return this;
    }

    public String getListenerFirstName(){
        return this.prefs.getString(LISTENER_FIRST_NAME_KEY,"");
    }

    public UserPreferences setListenerFirstName(final String firstName){
        prefs.edit().putString(LISTENER_FIRST_NAME_KEY,firstName);
        return this;
    }

    public String getEmail(){
        return this.prefs.getString(EMAIL_KEY,"");
    }

    public UserPreferences setEmail(final String emailValue){
        prefs.edit().putString(EMAIL_KEY, emailValue);
        return this;
    }

    public String getMusicJockeyStageName(){
        return this.prefs.getString(MUSIC_JOCKEY_STAGE_NAME_KEY,"");
    }

    public UserPreferences setMusicJockeyStageName(final String stageNameValue){
        prefs.edit().putString(MUSIC_JOCKEY_STAGE_NAME_KEY, stageNameValue);
        return this;
    }
}
