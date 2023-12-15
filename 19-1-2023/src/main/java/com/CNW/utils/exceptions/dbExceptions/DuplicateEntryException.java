package com.CNW.utils.exceptions.dbExceptions;

import com.CNW.utils.enums.ErrorCodes;
import com.CNW.utils.enums.ErrorStatusCodes;
import com.CNW.utils.exceptions.Exception;

public class DuplicateEntryException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public DuplicateEntryException(String message) {
        this.message = message;
        this.errorCode = ErrorCodes.DuplicateEntryException.getValue();
        this.statusCode = ErrorStatusCodes.DuplicateEntryException.getValue();
    }
}
