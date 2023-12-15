package com.CNW.utils.exceptions.authExceptions;

import com.CNW.utils.enums.ErrorCodes;
import com.CNW.utils.enums.ErrorStatusCodes;

public class InvalidCredentialsException extends com.CNW.utils.exceptions.Exception {

    /**
     *
     */
    private static final long serialVersionUID = 2L;

    public InvalidCredentialsException(String message) {
        this.message = message;
        this.errorCode = ErrorCodes.InvalidCredentialsException.getValue();
        this.statusCode = ErrorStatusCodes.InvalidCredentialsException.getValue();
    }
}
