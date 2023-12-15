package com.CNW.utils.exceptions.dbExceptions;

import com.CNW.utils.enums.ErrorCodes;
import com.CNW.utils.enums.ErrorStatusCodes;
import com.CNW.utils.exceptions.Exception;

public class CreateFailedException extends Exception {
    public CreateFailedException(String message) {
        this.message = message;
        this.errorCode = ErrorCodes.CreateFailedException.getValue();
        this.statusCode = ErrorStatusCodes.CreateFailedException.getValue();

    }
}
