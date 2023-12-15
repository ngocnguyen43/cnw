package com.CNW.utils.mapper;

import com.CNW.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements IMapper<User> {
    private boolean withDate = false;
    private boolean withPassword = false;

    public UserMapper() {
    }

    public UserMapper(boolean withDate, boolean withPassword) {
        this.withDate = withDate;
        this.withPassword = withPassword;
    }

    @Override
    public User mapRow(ResultSet result) {
        User user = new User();
        try {
            user.setId(result.getInt("id"));
//            user.setRoleId(result.getInt("role_id"));
            user.setEmail(result.getString("email"));
            user.setFullName(result.getString("full_name"));
            if (this.withPassword) user.setPassword(result.getString("password"));
            user.setRoleId(result.getInt("role"));
            user.setAddress(result.getString("address"));
            return user;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }
        return null;

    }

}
