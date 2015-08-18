package com.play4u.mobile.services.spotify.builders;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ykeyser on 8/17/15.
 * Builds the q param for Spotify, e.g. q=harlem+shake
 */
public class TrackSearchQuery {
    protected final String userQuery;
    protected static final String infoInfoRegexStr="(\\p{Alnum}+)";
    protected static final Pattern findInfoPattern=Pattern.compile(infoInfoRegexStr);

    public TrackSearchQuery(final String userQuery){
        this.userQuery=userQuery;
    }

    public String getUserQuery(){
        return userQuery;
    }

    public String build(){
        final Matcher matcher=findInfoPattern.matcher(this.userQuery.trim());
        final StringBuilder queryBuilder=new StringBuilder();

        while(matcher.find()){
            final String token=matcher.group();

            if(queryBuilder.length()>0) {
                queryBuilder.append('+');
            }

            queryBuilder.append(token);
        }

        return queryBuilder.toString();
    }
}
