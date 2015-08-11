package com.play4u.mobile.domain.adapters.json;

import com.play4u.mobile.domain.MusicJockey;

import org.json.JSONObject;

/**
 * Created by ykeyser on 8/11/15.
 */
public class MusicJockeyJsonAdapter extends UserJsonAdapter{
    public MusicJockeyJsonAdapter(final JSONObject jsonObject){
        super(jsonObject);
    }

    public MusicJockey adapt(){
        return null;
    }
}
