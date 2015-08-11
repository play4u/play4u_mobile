package com.play4u.mobile.services.exceptions;

import java.io.IOException;

/**
 * Created by ykeyser on 8/8/15.
 */
public class ServiceCommitException extends IOException {
    public ServiceCommitException(final String message){
        super(message);
    }
}
