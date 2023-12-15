package com.CNW.controllers.user;

import com.CNW.dtos.UserDTO;
import com.CNW.services.IUserService;
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

@WebServlet(urlPatterns = V1+"/user")
@MultipartConfig
public class UserRoute extends HttpServlet {

    @Inject
    private IUserService service;
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(UserDTO.class);
        ErrorHandler.handle(resp,()->this.service.update(dto));
    }

}
