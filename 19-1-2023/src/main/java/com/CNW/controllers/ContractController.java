package com.CNW.controllers;

import com.CNW.services.IContractService;
import com.CNW.utils.exceptions.ErrorHandler;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.CNW.utils.Constants.EndPoint.V1;

@WebServlet(urlPatterns = {V1+"/contract/*"})
public class ContractController extends HttpServlet {
    @Inject
    private IContractService contractService;

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf( req.getPathInfo().substring(1));
        ErrorHandler.handle(resp,()->this.contractService.cancelContracts(id));

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf( req.getPathInfo().substring(1));
        ErrorHandler.handle(resp,()->this.contractService.deleteOne(id));
    }
}
