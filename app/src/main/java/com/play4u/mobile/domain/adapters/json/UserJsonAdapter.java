package com.play4u.mobile.domain.adapters.json;

import com.play4u.mobile.domain.User;

import org.json.JSONObject;

/**
 * Created by ykeyser on 8/11/15.
 */
public class UserJsonAdapter {
    protected JSONObject jsonObject;

    public UserJsonAdapter(final JSONObject jsonObject){
        this.jsonObject=jsonObject;
    }

    public User adapt(){
        return null;
    }
}
