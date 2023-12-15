package com.CNW.services;

import com.CNW.dtos.UserDTO;
import com.CNW.utils.exceptions.dbExceptions.*;
import com.CNW.utils.response.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.hc.core5.http.NotImplementedException;

import java.util.InvalidPropertiesFormatException;

public interface IUserService {
    Message findAll(String role) throws InvalidPropertiesException, NotImplementedException;

//    Message findAll(UserPaginationDTO dto, String role) throws InvalidPropertiesException;

    Message findByUserId(String id);

    Message save(UserDTO dto) throws DuplicateEntryException, CreateFailedException, NotImplementedException;

    Message delete(Integer userId) throws InvalidPropertiesException, UnexpectedException, InvalidPropertiesFormatException, JsonProcessingException;

    Message findOne(Integer id);

    Message findAllMods();

    Message update(UserDTO dto) throws DuplicateEntryException, UpdateFailedException, UnexpectedException, NotFoundException, InvalidPropertiesException, NotImplementedException;
}
