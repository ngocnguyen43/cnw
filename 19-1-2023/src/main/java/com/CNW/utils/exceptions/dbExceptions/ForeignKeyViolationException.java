package com.CNW.utils.exceptions.dbExceptions;

import com.CNW.utils.enums.ErrorCodes;
import com.CNW.utils.enums.ErrorStatusCodes;
import com.CNW.utils.exceptions.Exception;

public class ForeignKeyViolationException extends Exception {
    public ForeignKeyViolationException(String message) {
        this.message = message;
        this.statusCode = ErrorStatusCodes.ForeignKeyViolationException.getValue();
        this.errorCode = ErrorCodes.ForeignKeyViolationException.getValue();
    }
}
