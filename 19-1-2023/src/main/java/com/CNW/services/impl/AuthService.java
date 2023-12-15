package com.CNW.services.impl;

import com.CNW.daos.IUserDAO;
import com.CNW.dtos.UserDTO;
import com.CNW.dtos.UserSigninDTO;
import com.CNW.models.User;
import com.CNW.services.IAuthService;
import com.CNW.utils.exceptions.authExceptions.InvalidCredentialsException;
import com.CNW.utils.exceptions.dbExceptions.CreateFailedException;
import com.CNW.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.CNW.utils.exceptions.dbExceptions.NotFoundException;
import com.CNW.utils.exceptions.dbExceptions.UnexpectedException;
import com.CNW.utils.helpers.*;
import com.CNW.utils.response.Data;
import com.CNW.utils.response.Message;
import com.CNW.utils.response.Meta;
import com.CNW.utils.response.Response;
import org.apache.hc.core5.http.NotImplementedException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class AuthService implements IAuthService {

    @Inject
    private IUserDAO iUserDAO;

    @Inject
    private IUserDAO userDAO;

    @Override
    public Message Login(UserSigninDTO dto) throws UnexpectedException, NotFoundException, InvalidCredentialsException {
        if (dto.getEmail() == null || dto.getPassword() == null)
            throw new InvalidCredentialsException(Response.INVALID_EMAIL_OR_PASSWORD);
        JWTGeneration JWT;
        HashMap<String, String> claims;
        try {
            claims = new HashMap<>();
            JWT = new JWTGeneration();
        } catch (NoSuchAlgorithmException e) {
            throw new UnexpectedException();
        }
        User user = iUserDAO.findByEmail(dto.getEmail());
        if (user == null) throw new NotFoundException(Response.USER_NOT_FOUND);
        String hashedPassword = user.getPassword();
//        try {

        if (!dto.getPassword().equals(hashedPassword) )
            throw new InvalidCredentialsException(Response.WRONG_PASSWORD);
        String userId = user.getId().toString();
        claims.put("userId", userId);
        String role = user.getRoleId().toString();
        String accessToken = JWT.generate(claims);

        Data data = new Data.Builder(accessToken).withRole(role).withId(userId).build();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.LOGIN_SUCCESS).build();
        return new Message.Builder(meta).withData(data).build();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new UnexpectedException();
//        }
    }

    @Override
    public Message Register(UserDTO dto) throws DuplicateEntryException, CreateFailedException, UnexpectedException, NotFoundException, InvalidCredentialsException, NotImplementedException {
        User isEmailExist = iUserDAO.findByEmail(dto.getEmail());
        if (isEmailExist != null)
            throw new DuplicateEntryException(Response.EMAIL_IN_USE);
//        User isNationalIdExist = iUserDAO.findByNationalId(dto.getNationalId());
//        if (isNationalIdExist != null)
//            throw new DuplicateEntryException(Response.NATIONAL_ID_IN_USE);
        User domain = Helper.objectMapper(dto, User.class);
//        String id = IDGeneration.generate();
////        String id = userDao.save(domainUser);
//        domain.setId(id);
//        if (type != null && type.equals("company")) {
//            String companyId = IDGeneration.generate();
////            String taxtId = IDGeneration.generate(10);
//            domain.setCompanyId(companyId);
////            domain.setTaxIndentity(taxtId);
//        }
        try {
            iUserDAO.save(domain);
            UserSigninDTO loginDto = Helper.objectMapper(dto, UserSigninDTO.class);
            return this.Login(loginDto);
        } catch (Exception e) {
            if (e instanceof UnexpectedException)
                throw new UnexpectedException();
            if (e instanceof NotFoundException)
                throw new NotFoundException(Response.USER_NOT_FOUND);
            if (e instanceof InvalidCredentialsException)
                throw new InvalidCredentialsException(Response.WRONG_PASSWORD);
            else
            {
                e.printStackTrace();
                throw new CreateFailedException(Response.REGISTERED_FAILED);

            }
        }
    }

    @Override
    public Message InspectorRegister(UserDTO dto, String userId) throws DuplicateEntryException, CreateFailedException, NotImplementedException {
//        User isEmailExist = iUserDAO.findByEmail(dto.getEmail());
//        String creator = userDAO.getUserRole(userId);
//
//        if (isEmailExist != null)
//            throw new DuplicateEntryException(Response.EMAIL_IN_USE);
//        User isNationalIdExist = iUserDAO.findByNationalId(dto.getNationalId());
//        if (isNationalIdExist != null)
//            throw new DuplicateEntryException(Response.NATIONAL_ID_IN_USE);
//        User domain = Helper.objectMapper(dto, User.class);
//        String id = IDGeneration.generate();
//        domain.setRoleId(2);
//        domain.setId(id);
//        Notification notification = new Notification
//                .Builder(IDGeneration.generate())
//                .withCreator(userId)
//                .withMods(Collections.singletonList(id))
//                .withMessage("You account has been created by " + creator)
//                .build();
//        try {
//            iUserDAO.save(domain);
//            iNotificationService.create(notification);
//            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(Response.CREATED).build();
//            return new Message.Builder(meta).build();
//        } catch (Exception e) {
//            throw new CreateFailedException(Response.CREATE_FAILED);
//        }
        throw new NotImplementedException();
    }



    @Override
    public Message ResetPassword(String id,String userId) throws NotFoundException, UnexpectedException {
        User user = iUserDAO.findByUserId(userId, true);
        if (user == null) throw new NotFoundException("User Not Found");
        String PASSWORD = "123456";
        String password = HashPassword.HashPW(PASSWORD);
        try {
            iUserDAO.updatePassword(userId, password);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnexpectedException();
        }
    }

}
