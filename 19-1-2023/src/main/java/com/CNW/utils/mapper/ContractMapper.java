package com.CNW.utils.mapper;

import com.CNW.models.ContractModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContractMapper implements IMapper<ContractModel> {
    @Override
    public ContractModel mapRow(ResultSet result) {
        ContractModel contract = new ContractModel();
        try {
            contract.setId(result.getInt("id"));
//            user.setRoleId(result.getInt("role_id"));
            contract.setUserId(result.getInt("userId"));
            contract.setName(result.getString("name"));
            contract.setStartDate(result.getTimestamp("start_date"));
            contract.setEndDate(result.getTimestamp("end_date"));
            contract.setIsCancel(result.getInt("is_cancel"));
            contract.setPrice(result.getInt("pricing"));
            return contract;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }
        return null;
    }
}
