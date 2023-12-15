package com.CNW.controllers;

import com.CNW.dtos.ContractDTO;
import com.CNW.services.IContractService;
import com.CNW.utils.exceptions.ErrorHandler;
import com.CNW.utils.helpers.Helper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.CNW.utils.Constants.EndPoint.V1;

@WebServlet(urlPatterns = {V1+"/contract/create"})
@MultipartConfig
public class CreateContractController extends HttpServlet {
    @Inject
    private IContractService service;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ContractDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(ContractDTO.class);
        ErrorHandler.handle(resp,()->this.service.createOne(dto));
    }

}
