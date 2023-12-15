package com.CNW.utils.exceptions.authExceptions;

import com.CNW.utils.enums.ErrorCodes;
import com.CNW.utils.enums.ErrorStatusCodes;
import com.CNW.utils.exceptions.Exception;

public class RegistrationFailedException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public RegistrationFailedException() {
        this.message = "User failed to be registered";
        this.errorCode = ErrorCodes.RegistrationFailedException.getValue();
        this.statusCode = ErrorStatusCodes.RegistrationFailedException.getValue();
    }

}
