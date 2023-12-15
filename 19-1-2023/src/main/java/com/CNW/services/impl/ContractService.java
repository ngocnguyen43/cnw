package com.CNW.services.impl;

import com.CNW.daos.IContractDAO;
import com.CNW.dtos.ContractDTO;
import com.CNW.models.ContractModel;
import com.CNW.services.IContractService;
import com.CNW.utils.exceptions.dbExceptions.InvalidPropertiesException;
import com.CNW.utils.exceptions.dbExceptions.UnexpectedException;
import com.CNW.utils.helpers.Helper;
import com.CNW.utils.response.Data;
import com.CNW.utils.response.Message;
import com.CNW.utils.response.Meta;
import com.CNW.utils.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.core5.http.NotImplementedException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ContractService implements IContractService {
    @Inject
    private IContractDAO iContractDAO;


    @Override
    public Message findAll() throws InvalidPropertiesException, NotImplementedException {
        List<ContractModel> contractModels = iContractDAO.getAll();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        Data data = new Data.Builder(null).withResults(contractModels).build();
        return new Message.Builder(meta).withData(data).build();

    }

    @Override
    public Message cancelContracts(Integer id) throws UnexpectedException {
        try{
            this.iContractDAO.cancelContract(id);
        }catch (Exception e){
            e.printStackTrace();
            throw  new UnexpectedException();
        }
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        return new Message.Builder(meta).build();
    }

    @Override
    public Message getByUserId(Integer id) throws UnexpectedException {
        try {
            List<ContractModel> contractModels = this.iContractDAO.getAllWithUserId(id);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
            Data data = new Data.Builder(null).withResults(contractModels).build();
            return new Message.Builder(meta).withData(data).build();
        }catch (Exception e){
            throw  new UnexpectedException();
        }
    }

    @Override
    public Message createOne(ContractDTO dto) throws UnexpectedException {
        try {
            System.out.println(new ObjectMapper().writeValueAsString(dto));
            ContractModel model = Helper.objectMapper(dto,ContractModel.class);
            System.out.println(new ObjectMapper().writeValueAsString(model));
            this.iContractDAO.createOne(model);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(Response.CREATED).build();
            return new Message.Builder(meta).build();
        }catch (Exception e){
            throw  new UnexpectedException();
        }
    }

    @Override
    public Message deleteOne(Integer id) throws UnexpectedException {
        try {
            this.iContractDAO.deleteOne(id);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_NO_CONTENT).build();
            return new Message.Builder(meta).build();
        }catch (Exception e){
            throw  new UnexpectedException();
        }
    }
}
