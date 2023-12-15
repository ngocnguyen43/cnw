package com.CNW.services;

import com.CNW.dtos.UserDTO;
import com.CNW.dtos.UserSigninDTO;
import com.CNW.utils.exceptions.authExceptions.InvalidCredentialsException;
import com.CNW.utils.exceptions.dbExceptions.CreateFailedException;
import com.CNW.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.CNW.utils.exceptions.dbExceptions.NotFoundException;
import com.CNW.utils.exceptions.dbExceptions.UnexpectedException;
import com.CNW.utils.response.Message;
import org.apache.hc.core5.http.NotImplementedException;

public interface IAuthService {
    Message Login(UserSigninDTO dto) throws UnexpectedException, NotFoundException, InvalidCredentialsException;

    Message Register(UserDTO user) throws DuplicateEntryException, CreateFailedException, UnexpectedException, NotFoundException, InvalidCredentialsException, NotImplementedException;

    Message InspectorRegister(UserDTO dto, String userId) throws DuplicateEntryException, CreateFailedException, NotImplementedException;


    Message ResetPassword(String id,String userId) throws NotFoundException, UnexpectedException;
}
