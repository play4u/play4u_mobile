package com.play4u.mobile.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by ykeyser on 8/8/15.
 */
public class DirtyEditText {
    final EditText editText;
    protected boolean isDirty;
    protected int countBefore,countAfter;
    protected String textBefore="", textAfter="";

    public DirtyEditText(final EditText editText){
        this.editText=editText;

        this.editText.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                countBefore=count;
                textBefore=s.toString();
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                countAfter=count;
                textAfter=s.toString();
            }

            public void afterTextChanged(Editable s) {
                if(countBefore!=countAfter || !StringUtils.equalsIgnoreCase(textBefore,textAfter)){
                    isDirty=true;
                }else{
                    isDirty=false;
                }

            }
        });
    }

    public boolean isDirty(){
        return isDirty;
    }

    public String toString(){
        return editText.getText().toString();
    }
}
