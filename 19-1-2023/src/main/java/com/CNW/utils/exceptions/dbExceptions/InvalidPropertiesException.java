package com.CNW.utils.exceptions.dbExceptions;

import com.CNW.utils.enums.ErrorCodes;
import com.CNW.utils.enums.ErrorStatusCodes;
import com.CNW.utils.exceptions.Exception;

public class InvalidPropertiesException extends Exception {
    public InvalidPropertiesException(String message) {
        this.message = message;
        this.errorCode = ErrorCodes.InvalidPropertiesException.getValue();
        this.statusCode = ErrorStatusCodes.InvalidPropertiesException.getValue();
    }
}
