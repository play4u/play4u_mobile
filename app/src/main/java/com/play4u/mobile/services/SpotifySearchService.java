package com.play4u.mobile.services;

import java.util.ArrayList;
import java.util.List;

import kaaes.spotify.webapi.android.models.AlbumsPager;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import kaaes.spotify.webapi.android.models.TracksPager;
import retrofit.Callback;

/**
 * Created by ykeyser on 8/17/15.
 */

interface TrackFoundObserver extends Callback<TracksPager>{}
interface ArtistFoundObserver extends Callback<ArtistsPager>{}
interface AlbumFoundObserver extends Callback<AlbumsPager>{}

public class SpotifySearchService {
    protected List trackFoundObservers=new ArrayList();
    protected List artistFoundObservers=new ArrayList();
    protected List albumFoundObservers=new ArrayList();

    public SpotifySearchService addAlbumFoundObserver(final AlbumFoundObserver observer){
        albumFoundObservers.add(observer);
        return this;
    }

    public SpotifySearchService addTrackFoundObserver(final TrackFoundObserver observer){
        trackFoundObservers.add(observer);
        return this;
    }

    public SpotifySearchService addArtistFoundObserver(final ArtistFoundObserver observer){
        artistFoundObservers.add(observer);
        return this;
    }
}