package com.CNW.daos;

import com.CNW.models.User;
import com.CNW.models.pagination.UserPagination;

import java.util.List;

public interface IUserDAO extends GenericDAO<User> {
    List<User> findAll();

    List<User> findAll(String role, UserPagination pagination);

    User findByUserId(Integer id);

    User findByUserId(String id, boolean withPassword);

    User findByEmail(String email);

    User findByEmailActive(String email);

    User findByCompanyId(String companyId);

    User findByNationalId(String nationalId);

    Integer countAllRecord(UserPagination pagination, String role);

    void save(User user);

    void update(User user,Integer id);

    void deleteUser(Integer USerId);

    List<User> getAllMods();

    void updatePassword(String id, String password);

    String getUserRole(String id);

    String getUserName(String id);


}
