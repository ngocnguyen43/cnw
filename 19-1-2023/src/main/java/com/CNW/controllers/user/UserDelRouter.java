package com.CNW.controllers.user;

import com.CNW.services.IUserService;
import com.CNW.utils.exceptions.ErrorHandler;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.CNW.utils.Constants.EndPoint.V1;

@WebServlet(urlPatterns = V1+"/user/*")
@MultipartConfig
public class UserDelRouter extends HttpServlet {
    @Inject
    private IUserService service;

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Integer id =  Integer.valueOf(req.getPathInfo().substring(1));
       ErrorHandler.handle(resp,()->this.service.delete(id));
    }
}
