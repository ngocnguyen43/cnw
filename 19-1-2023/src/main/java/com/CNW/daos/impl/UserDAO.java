package com.CNW.daos.impl;

import com.CNW.daos.IUserDAO;
import com.CNW.models.User;
import com.CNW.models.pagination.UserPagination;
import com.CNW.utils.mapper.CountMapper;
import com.CNW.utils.mapper.UserMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import static com.CNW.utils.Constants.Pagination.PER_PAGE;

public class UserDAO extends AbstractDAO<User> implements IUserDAO {

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
//        String sql = "SELECT * FROM users INNER JOIN roles ON users.roleId = roles.roleId";
        final String sql_admin = " SELECT * from users ";
//		System.out.println(query(sql, new UserMapper()));

        return query(sql_admin, new UserMapper());
    }

    @Override
    public List<User> findAll(String role, UserPagination pagination) {
        String sql = "SELECT * FROM login.users  LEFT JOIN login.business ON users.business_id = business.business_id " +
                "INNER JOIN login.roles ON login.users.role_id = login.roles.role_id  WHERE ";
        if (pagination.getFullname() != null)
            sql += " login.users.full_name LIKE '%" + pagination.getFullname() + "%' ";
        else sql += " true ";
        if (pagination.getEmail() != null) sql += " AND login.users.email LIKE '%" + pagination.getEmail() + "%'";
        else sql += "AND true ";
        if (role.equals("ADMIN")) sql += "AND login.users.role_id != 1";
        if (role.equals("MODERATOR")) sql += "AND login.users.role_id = 3";
        sql += " ORDER BY login.users.role_id ASC, login.users.created_at DESC LIMIT " + PER_PAGE + " OFFSET " + (pagination.getPage() - 1) * PER_PAGE;
        System.out.println(sql);
        return query(sql, new UserMapper());
    }

    @Override
    public User findByUserId(Integer id) {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM users  WHERE id = ?";
        List<User> users = query(sql, new UserMapper(false,true), id);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User findByUserId(String id, boolean withPassword) {
        String sql = "SELECT * FROM users  INNER JOIN roles ON users.role_id = roles.role_id WHERE user_id = ?";
        List<User> users = query(sql, new UserMapper(false, withPassword), id);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public void save(User user) {
        // TODO Auto-generated method stub

        String sql = "INSERT INTO users (full_name," + "address," + "email," + "password," + "role)" + " VALUES(?,?,?,?,?)";
        // helper
        // helper hashed password
//		String taxId = user.getTaxIndentity() != null ? user.getTaxIndentity() : null;
        insert(sql, user.getFullName(), user.getAddress(), user.getEmail(), user.getPassword(), user.getRoleId());

    }

    @Override
    public void update(User user,Integer id) {

        String sql = "UPDATE users SET " + "email = ?, " + "full_name= ?," + "address= ?," +"password= ? " + "WHERE id = ? ";
        update(sql, user.getEmail(), user.getFullName(), user.getAddress(), user.getPassword(),id);
    }

    public void deleteUser(Integer userId) {
        // TODO Auto-generated method stub
        User user = findByUserId(userId);
        try {
            System.out.println(new ObjectMapper().writeValueAsString(user));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
//        int action = user.getAction() == 0 ? 1 : 0;
        String sql = "DELETE FROM users WHERE id = ?";
        delete(sql,  user.getId());

    }

    @Override
    public List<User> getAllMods() {
        String sql = "SELECT * FROM users  INNER JOIN roles ON users.role_id = roles.role_id WHERE users.role_id  = 2 ORDER BY created_at";
        return query(sql, new UserMapper());
    }

    @Override
    public void updatePassword(String id, String password) {
        String sql = "UPDATE login.users SET password = ? WHERE users.user_id = ?";
        update(sql, password, id);
    }

    @Override
    public String getUserRole(String id) {
        String sql = "SELECT * FROM login.users " +
//                "INNER JOIN login.business ON users.business_id = business.business_id " +
                "INNER JOIN roles ON users.role_id = roles.role_id WHERE login.users.user_id = ?";
        List<User> users = query(sql, new UserMapper(), id);
        return users.isEmpty() ? null : users.get(0).getRoleId().toString();
    }

    @Override
    public String getUserName(String id) {
        String sql = "SELECT * FROM login.users  " +
                "INNER JOIN roles ON users.role_id = roles.role_id WHERE login.users.user_id = ?";
        List<User> users = query(sql, new UserMapper(), id);
        return users.isEmpty() ? null : users.get(0).getFullName();
    }



    @Override
    public User findByEmail(String email) {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM users  " +
                " WHERE users.email = ? ";
//		String sql = "SELECT * FROM users INNER JOIN roles ON users.roleId = roles.roleId WHERE email = ? ";

        List<User> users = query(sql, new UserMapper(false, true), email);

        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User findByEmailActive(String email) {
        String sql = "SELECT * FROM users  " +
                " WHERE users.email = ? ";
//		String sql = "SELECT * FROM users INNER JOIN roles ON users.roleId = roles.roleId WHERE email = ? ";

        List<User> users = query(sql, new UserMapper(false, true), email);

        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User findByCompanyId(String companyId) {
        String sql = "SELECT * FROM users  " +
                "INNER JOIN login.business ON users.business_id = business.business_id " +
                "INNER JOIN roles ON users.role_id = roles.role_id WHERE company_id = ? ";
//		String sql = "SELECT * FROM users INNER JOIN roles ON users.roleId = roles.roleId WHERE email = ? ";

        List<User> users = query(sql, new UserMapper(), companyId);

        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User findByNationalId(String nationalId) {
        String sql = "SELECT * FROM users " +
                "INNER JOIN roles ON users.role_id = roles.role_Id WHERE national_id = ?";
        // TODO Auto-generated method stub
        List<User> users = query(sql, new UserMapper(false, true), nationalId);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public Integer countAllRecord(UserPagination pagination, String role) {
        String sql = "SELECT COUNT(user_id) as total FROM login.users  INNER JOIN login.roles ON login.users.role_id = login.roles.role_id  WHERE ";
        if (pagination.getFullname() != null)
            sql += " login.users.full_name LIKE '%" + pagination.getFullname() + "%' ";
        else sql += " true ";
        if (pagination.getEmail() != null) sql += " AND login.users.email LIKE '%" + pagination.getEmail() + "%'";
        else sql += "AND true ";
        if (role.equals("ADMIN")) sql += "AND login.users.role_id != 1";
        if (role.equals("MODERATOR")) sql += "AND login.users.role_id = 3";
        List<Integer> pages = query(sql, new CountMapper());
        return pages.isEmpty() ? null : pages.get(0);
    }

}
