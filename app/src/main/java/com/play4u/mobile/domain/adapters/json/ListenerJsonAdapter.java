package com.play4u.mobile.domain.adapters.json;

import com.play4u.mobile.domain.Listener;

import org.json.JSONObject;

/**
 * Created by ykeyser on 8/11/15.
 */
public class ListenerJsonAdapter extends UserJsonAdapter {
    public ListenerJsonAdapter(final JSONObject jsonObject){
        super(jsonObject);
    }

    public Listener adapt(){
        return null;
    }
}
