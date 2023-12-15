package com.CNW.controllers.auth;

import com.CNW.services.IAuthService;
import com.CNW.utils.exceptions.ErrorHandler;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.CNW.utils.Constants.EndPoint.*;

@MultipartConfig
@WebServlet(urlPatterns = {V1 + PRIVATE + ADMIN + "/reset"})
public class AdminResetPassword extends HttpServlet {
    @Inject
    private IAuthService authService;

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("userId");
        ErrorHandler.handle(resp, () -> authService.ResetPassword(req.getHeader("client_id"), id));
    }
}
