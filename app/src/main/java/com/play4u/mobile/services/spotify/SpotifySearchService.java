package com.play4u.mobile.services.spotify;

import com.play4u.mobile.services.singletons.SpotifyApiClientSingleton;
import com.play4u.mobile.services.spotify.builders.TrackSearchQuery;

import java.util.HashMap;
import java.util.Map;

/**
 * Adapter for Spotify service
 * Created by ykeyser on 8/17/15.
 */
public class SpotifySearchService {
    public static final String LIMIT_PARAM="limit";
    public static final String MARKET_PARAM="market";
    public static final String GENRE_PARAM="genre";
    protected TrackFoundObserver trackFoundObserver;
    protected static final String LOG_TAG="SpotifySearchService";
    protected final Map<String, Object> queryParams=new HashMap<String, Object>();

    public SpotifySearchService setQueryParam(final String name, final String value){
        queryParams.put(name,value);
        return this;
    }

    public String getQueryParam(final String name){
        return queryParams.get(name).toString();
    }

    public SpotifySearchService setTrackFoundObserver(final TrackFoundObserver observer){
        this.trackFoundObserver=observer;
        return this;
    }

    public SpotifySearchService findTrack(final TrackSearchQuery query){
        if(!(trackFoundObserver instanceof TrackFoundObserver)){
            throw new IllegalArgumentException("Track-found observer is missing");
        }else if(!(query instanceof TrackSearchQuery)){
            throw new IllegalArgumentException("Track-search query is missing");
        }
        else if(query.getUserQuery().isEmpty()){
            throw new IllegalArgumentException("Track-search query is empty");
        }

        SpotifyApiClientSingleton.singleton().searchTracks(query.build(), queryParams, trackFoundObserver);
        return this;
    }
}