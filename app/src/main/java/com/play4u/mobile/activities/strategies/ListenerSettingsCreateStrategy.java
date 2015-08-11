package com.play4u.mobile.activities.strategies;

import android.util.Log;

import com.play4u.mobile.activities.ListenerSettingsActivity;
import com.play4u.mobile.services.ListenerSettingsService;

import org.json.JSONObject;

/**
 * Created by ykeyser on 8/10/15.
 */
public class ListenerSettingsCreateStrategy extends UserSettingsCreateStrategy{
    protected static final String LOG_TAG="ListenerSettingsCreate";

    public ListenerSettingsCreateStrategy(final ListenerSettingsActivity activity, final ListenerSettingsService service){
        super(activity);
        this.setService(service);
    }

    public ListenerSettingsActivity getActivity(){
        return  (ListenerSettingsActivity)super.getActivity();
    }

    public void updateData(){
        super.updateData();
        updateFirstName();
    }

    protected ListenerSettingsService getService(){
        return (ListenerSettingsService)super.getService();
    }

    protected void updateFirstName() {
        if(getActivity().getFirstNameTextInput().isDirty()) {
            getService().setFirstName(getActivity().getEmailTextInput().toString());
        }
    }

    public void handleServiceResponse(final JSONObject jsonObj){
        if(jsonObj == null){
            throw new IllegalArgumentException("JSON response object is blank");
        }

        Log.i(LOG_TAG, "[ListenerSettingsCreateStrategy] handle json: "+jsonObj);

        return;
    }
}
