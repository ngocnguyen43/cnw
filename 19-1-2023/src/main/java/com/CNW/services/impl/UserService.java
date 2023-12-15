package com.CNW.services.impl;

import com.CNW.daos.*;
import com.CNW.dtos.UserDTO;
import com.CNW.models.*;
import com.CNW.services.IUserService;
import com.CNW.utils.exceptions.dbExceptions.*;
import com.CNW.utils.response.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.core5.http.NotImplementedException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

public class UserService implements IUserService {

    @Inject
    private IUserDAO userDao;

    @Override
    public Message findAll(String role) throws InvalidPropertiesException, NotImplementedException {
//        // TODO Auto-generated method stub
//        String[] roles = {"ADMIN", "MOD"};
//        if (!ArrayUtils.contains(roles, role)) throw new InvalidPropertiesException("Invalid Role");
        List<User> users = userDao.findAll();
//        for (User user : users) {
//            Business business = businessDAO.findOneById(user.getBusinessId());
//            user.setBusiness(business);
//            List<ProductModel> products = productDAO.findAllByUserId(user.getId());
//            for (ProductModel product : products) {
//                List<ProductCertificatesModel> productCertificates = productCertificateDAO.findAllById(product.getId());
//                KindOfProductModel kindOfProductModel = kindOfProductDAO.findOne(product.getKindof());
//                product.setKindOfProductModel(kindOfProductModel);
//                List<Certificate> certificates = new ArrayList<>();
//                for (ProductCertificatesModel productCertificate : productCertificates) {
//                    Certificate certificate = iCertificateDAO.findOne(productCertificate.getCertificateId());
//                    certificates.add(certificate);
//                }
//                product.setCertificate(certificates);
//            }
//            user.setProductModel(products);
//        }
////        if (users == null) throw new NotFoundException("Not Found Users");
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        Data data = new Data.Builder(null).withResults(users).build();
        return new Message.Builder(meta).withData(data).build();
    }


    @Override
    public Message findByUserId(String id) {
        // TODO Auto-generated method stub
//		Message message = new Message();
//		User user = userDao.findByUserId(id);
//		if (user != null) {
//			Meta header = new Meta.Builder(HttpServletResponse.SC_OK).withErrCode(0).withMessage("User has found!").build();
//			UserDTO userDTO = Helper.objectMapper(user, UserDTO.class);
//			userDTO.setPassword(null);
//			userDTO.setRoleId(null);
//			Data body = new Data.Builder(null).withResults(userDTO).build();
//			
//			message.setBody(body);
//			message.setHeader(header);
//		}else {
//			Meta header = new Meta.Builder(HttpServletResponse.SC_NOT_FOUND).withErrCode(6).withMessage("User not found!").build();
//			message.setHeader(header);
//		}
        return null;
    }

    @Override
    public Message save(UserDTO dto) throws DuplicateEntryException, CreateFailedException, NotImplementedException {
//        boolean isEmailExist = userDao.findByEmail(dto.getEmail()) != null;
//        if (isEmailExist) throw new DuplicateEntryException("Email has Already Registered");
//        boolean isNationalIdExist = userDao.findByNationalId(dto.getNationalId()) != null;
//        if (isNationalIdExist) throw new DuplicateEntryException("National Id has Already Registered");
//        User domain = Helper.objectMapper(dto, User.class);
//        String id = IDGeneration.generate();
//        domain.setId(id);
//        if (dto.getBusinessId() != null) {
//            String companyId = IDGeneration.generate();
//            domain.setCompanyId(companyId);
//        }
//        //domain.setPassword(HashPassword.HashPW(domain.getPassword()));
//        try {
//            userDao.save(domain);
//            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(Response.CREATED).build();
//            return new Message.Builder(meta).build();
//        } catch (Exception e) {
//            throw new CreateFailedException(Response.CREATE_FAILED);
//        }
        throw  new NotImplementedException();
    }

    @Override
    public Message delete(Integer userId) throws InvalidPropertiesException, UnexpectedException, InvalidPropertiesFormatException, JsonProcessingException {

        userDao.deleteUser(userId);
//        iNotificationService.create(notification);
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        return new Message.Builder(meta).build();
    }

    @Override
    public Message findOne(Integer id) {
        User user = userDao.findByUserId(id);
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        Data data = new Data.Builder(null).withResults(user).build();
        return new Message.Builder(meta).withData(data).build();
    }

    @Override
    public Message findAllMods() {
        List<User> users = userDao.getAllMods();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        Data data = new Data.Builder(null).withResults(users).build();
        return new Message.Builder(meta).withData(data).build();
    }

    @Override
    public Message update(UserDTO dto) throws DuplicateEntryException, UpdateFailedException, NotFoundException, InvalidPropertiesException, NotImplementedException {
        if (dto.getId() == null) throw new InvalidPropertiesException("Invalid properties");
//        String role = userDao.getUserRole(userId);
//        String uRole = userDao.getUserRole(id);
//        System.out.println(role);
//        Notification notification = new Notification
//                .Builder(IDGeneration.generate())
//                .withCreator(userId)
//                .withMods(uRole.equals("Moderator") ? Collections.singletonList(id) : null)
//                .withUsers(uRole.equals("Store") ? Collections.singletonList(id) : null)
//                .withMessage("You was updated by " + role)
//                .build();
//        if (id == null) throw new InvalidPropertiesException("Invalid Property");
        if (dto.getEmail() != null) {
            boolean isEmailExist = userDao.findByEmail(dto.getEmail()) != null;
            if (isEmailExist) throw new DuplicateEntryException("Email already in use");
        }
//        if (dto.getNationalId() != null) {
//            boolean isNationalIdExist = userDao.findByNationalId(dto.getNationalId()) != null;
//            if (isNationalIdExist) throw new NotFoundException("National id already in use");
//        }
        User oldUser = userDao.findByUserId(dto.getId());
        if (oldUser == null){
            throw new NotFoundException("User not found");
        }
        User user = new User();
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        } else {
            user.setEmail(oldUser.getEmail());
        }
        if (dto.getFullName() != null) {
            user.setFullName(dto.getFullName());
        } else {
            user.setFullName(oldUser.getFullName());
        }
        if (dto.getPassword() != null) {
            user.setPassword(dto.getPassword());
        }else {
            user.setPassword(oldUser.getPassword());
        }
        if(dto.getAddress() != null){
            user.setAddress(dto.getAddress());
        }else {
            user.setAddress(oldUser.getAddress());
        }
//        user.setId(id);
        try {
            System.out.println(new ObjectMapper().writeValueAsString(oldUser));
            userDao.update(user,dto.getId());
        } catch (Exception e) {
            throw new UpdateFailedException("Update User Failed");
        }
        Meta meta = new Meta.Builder(HttpServletResponse.SC_NO_CONTENT).build();
        return new Message.Builder(meta).build();
    }



//	@Override
//	public List<Role> findAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
