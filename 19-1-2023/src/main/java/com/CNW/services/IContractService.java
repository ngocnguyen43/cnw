package com.CNW.services;

import com.CNW.dtos.ContractDTO;
import com.CNW.utils.exceptions.dbExceptions.InvalidPropertiesException;
import com.CNW.utils.exceptions.dbExceptions.UnexpectedException;
import com.CNW.utils.response.Message;
import org.apache.hc.core5.http.NotImplementedException;

public interface IContractService {

    Message findAll() throws InvalidPropertiesException, NotImplementedException;
    Message cancelContracts(Integer id) throws UnexpectedException;
    Message getByUserId(Integer id) throws UnexpectedException;
    Message createOne(ContractDTO dto) throws UnexpectedException;

    Message deleteOne(Integer id) throws UnexpectedException;
}
