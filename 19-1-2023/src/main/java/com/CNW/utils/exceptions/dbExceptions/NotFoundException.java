package com.CNW.utils.exceptions.dbExceptions;

import com.CNW.utils.enums.ErrorCodes;
import com.CNW.utils.enums.ErrorStatusCodes;

public class NotFoundException extends com.CNW.utils.exceptions.Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NotFoundException(String message) {
        this.message = message;
        this.errorCode = ErrorCodes.NotFoundException.getValue();
        this.statusCode = ErrorStatusCodes.NotFoundException.getValue();
    }
}
