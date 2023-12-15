package com.CNW.utils.exceptions.dbExceptions;

import com.CNW.utils.enums.ErrorCodes;
import com.CNW.utils.enums.ErrorStatusCodes;
import com.CNW.utils.exceptions.Exception;

public class UpdateFailedException extends Exception {
    public UpdateFailedException(String message) {
        this.message = message;
        this.errorCode = ErrorCodes.UpdateFailedException.getValue();
        this.statusCode = ErrorStatusCodes.UpdateFailedException.getValue();
    }
}
