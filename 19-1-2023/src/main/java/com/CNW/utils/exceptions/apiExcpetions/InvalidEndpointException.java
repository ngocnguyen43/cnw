package com.CNW.utils.exceptions.apiExcpetions;

import com.CNW.utils.enums.ErrorCodes;
import com.CNW.utils.enums.ErrorStatusCodes;

public class InvalidEndpointException extends com.CNW.utils.exceptions.Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public InvalidEndpointException() {
        this.errorCode = ErrorCodes.InvalidEndpointException.getValue();
        this.statusCode = ErrorStatusCodes.InvalidEndpointException.getValue();
        this.message = "URL Not Found";
    }
}
