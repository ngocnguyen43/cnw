package com.CNW.utils.exceptions.authExceptions;

import com.CNW.utils.enums.ErrorCodes;
import com.CNW.utils.enums.ErrorStatusCodes;

public class UnauthorizedException extends com.CNW.utils.exceptions.Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UnauthorizedException() {
        this.message = "User unauthorized for action";
        this.errorCode = ErrorCodes.UnauthorizedException.getValue();
        this.statusCode = ErrorStatusCodes.UnauthorizedException.getValue();
    }
}
