package com.play4u.mobile.services.spotify;

import com.play4u.mobile.services.singletons.SpotifyApiClientSingleton;
import com.play4u.mobile.services.spotify.builders.TrackSearchQuery;

/**
 * Adapter for Spotify service
 * Created by ykeyser on 8/17/15.
 */
public class SpotifySearchService {
    protected TrackFoundObserver trackFoundObserver;
    protected static final String LOG_TAG="SpotifySearchService";

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

        SpotifyApiClientSingleton.singleton().searchTracks(query.build(), trackFoundObserver);
        return this;
    }
}