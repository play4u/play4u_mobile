package com.play4u.mobile.services.spotify;

import kaaes.spotify.webapi.android.models.TracksPager;
import retrofit.Callback;

/**
 * Created by ykeyser on 8/17/15.
 */
public interface TrackFoundObserver extends Callback<TracksPager>{}
