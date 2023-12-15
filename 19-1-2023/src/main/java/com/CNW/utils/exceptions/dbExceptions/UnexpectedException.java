package com.CNW.utils.exceptions.dbExceptions;

import com.CNW.utils.enums.ErrorCodes;
import com.CNW.utils.enums.ErrorStatusCodes;
import com.CNW.utils.exceptions.Exception;

public class UnexpectedException extends Exception {
    public UnexpectedException() {
        this.message = "Somethings Went Wrongs";
        this.statusCode = ErrorStatusCodes.UnexpectedException.getValue();
        this.errorCode = ErrorCodes.UnexpectedException.getValue();
    }
}
