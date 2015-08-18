package com.play4u.mobile.services.spotify.builders;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TrackSearchQuerySpec{
    /*
        Array of tuples where each tuple is {input value, expected value}
     */
    @DataProvider(name="query_strings_provider")
    public String[][] create_tuples(){
        return new String[][]{
                {"harlem", "harlem"},
                {"harlem shake", "harlem+shake"},
                {"Harlem","Harlem"},
                {"canaro, Tango", "canaro+Tango"},
                {"Blink182", "Blink182"},
                {"311","311"},
                {"harlem shake shake", "harlem+shake+shake"}
        };
    }

    @Test(dataProvider = "query_strings_provider")
    public void testSongSearch(final String userInput, final String queryExpected){
        final TrackSearchQuery query=new TrackSearchQuery(userInput);
        final String actualQuery=query.build();
        assertThat(actualQuery,equalTo(queryExpected));
    }
}