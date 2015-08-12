package com.play4u.mobile.activities.strategies;

import android.content.Intent;
import android.util.Log;

import com.play4u.mobile.activities.ListenerActivity;
import com.play4u.mobile.activities.ListenerSettingsActivity;
import com.play4u.mobile.domain.Listener;
import com.play4u.mobile.services.ListenerSettingsService;

import org.json.JSONObject;

/**
 * Created by ykeyser on 8/10/15.
 */
public class ListenerSettingsStrategy extends UserSettingsStrategy {
    protected static final String LOG_TAG="Listener";

    public ListenerSettingsStrategy(final ListenerSettingsActivity activity, final ListenerSettingsService service){
        super(activity);
        this.setService(service);
    }

    protected void transitionToNextActivity(){
        final Intent intent = new Intent(getActivity(), ListenerActivity.class);
        getActivity().startActivity(intent);
    }

    public ListenerSettingsActivity getActivity(){
        return  (ListenerSettingsActivity)super.getActivity();
    }

    protected void updateData(){
        super.updateData();
        updateFirstName();
    }

    protected ListenerSettingsService getService(){
        return (ListenerSettingsService)super.getService();
    }

    protected void updateFirstName() {
        if(getActivity().getFirstNameTextInput().isDirty()) {
            getService().setFirstName(getActivity().getFirstNameTextInput().toString());
        }
    }

    protected void handleServiceResponse(final JSONObject jsonObj){
        if(jsonObj == null){
            throw new IllegalArgumentException("JSON response object is blank");
        }

        Log.i(LOG_TAG, "[ListenerSettingsStrategy] handle json: "+jsonObj);

        final Listener listener = getService().getUser();

        return;
    }
}
