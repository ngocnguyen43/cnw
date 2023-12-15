package com.CNW.filters;

import com.CNW.config.ResponseConfig;
import com.CNW.filters.checkRole.CheckRole;
import com.CNW.utils.Constants.EndPoint;
import com.CNW.utils.exceptions.ErrorHandler;
import com.CNW.utils.response.Message;
import com.CNW.utils.response.Meta;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {EndPoint.V1 + EndPoint.PRIVATE + EndPoint.SUPERVISOR + "/*"})
public class ModeratorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
//        ResponseConfig.ConfigHeader((HttpServletResponse) servletResponse);
        if (CheckRole.check(req, "MOD")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ResponseConfig.ConfigHeader(res);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_FORBIDDEN).withMessage("Forbidden!").build();
            ErrorHandler.handle(res, new Message.Builder(meta).build());
        }
    }

    @Override
    public void destroy() {

    }
}
