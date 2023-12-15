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

import static com.CNW.utils.Constants.EndPoint.ADMIN;
import static com.CNW.utils.Constants.EndPoint.V1;

@WebServlet(urlPatterns ={V1+ADMIN+"/users"})
@MultipartConfig
public class AdminUsers extends HttpServlet {

    @Inject
    private IUserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErrorHandler.handle(resp,()->userService.findAll("1"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		User user = new User();
//		user.setAction(1);
//		user.setEmail("mcncnbcsss");
//		user.setFirstName("test servlet");
//		user.setLastName("bingnngg");	
//		user.setNationalId("AbcAbcc");
//		user.setPassword("123456");
//		user.setPhoneNumber("098765432");
//		user.setRoleId(1);
//		user.setGender(1);
//		user.setUserName("aloalo");
//		user.setModifiedBy("d4216c1a-fabe-4dea-9b9e-84271c784497");
//		String id = userService.save(user);
//		System.out.println(id);
//		PrintWriter out = resp.getWriter();
//		ObjectMapper objectMapper= new ObjectMapper();
//		String jsonString = objectMapper.writeValueAsString(id);
//		resp.setContentType("application/json");
//		resp.setCharacterEncoding("UTF-8");
//		out.print(jsonString);
//		out.flush();
//		super.doPost(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub

    }

}
