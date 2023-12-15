package com.CNW.daos.impl;

import com.CNW.daos.IContractDAO;
import com.CNW.models.ContractModel;
import com.CNW.utils.mapper.ContractMapper;

import java.util.List;

public class ContractDAO extends AbstractDAO<ContractModel> implements IContractDAO {
    @Override
    public List<ContractModel> getAll() {
        final String sql_admin = " SELECT * from contracts ";
        return query(sql_admin, new ContractMapper());
    }

    @Override
    public void updateContract(Integer id, ContractModel model) {
        final String sql_admin = " UPDATE  contracts SET is_cancel =  ";
    }

    @Override
    public void cancelContract(Integer id) {
        ContractModel old = this.getOne(id);

        final String sql = "UPDATE contracts SET is_cancel = " +( old.getIsCancel()) + " WHERE id = ?";

        update(sql,id);
    }

    @Override
    public ContractModel getOne(Integer id) {
        String sql = "SELECT * FROM contracts  WHERE id = ?";
        List<ContractModel> users = query(sql, new ContractMapper(), id);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public void createOne(ContractModel model) {
        String sql = "INSERT INTO contracts (userId,name,start_date,end_date,is_cancel,pricing) VALUES (?,?,?,?,?,?)";
        insert(sql,model.getUserId(),model.getName(),model.getStartDate(),model.getEndDate(),model.getIsCancel(),model.getPrice());
    }

    @Override
    public List<ContractModel> getAllWithUserId(Integer id) {
        String sql = "SELECT * FROM contracts  WHERE userId = ?";
        return query(sql, new ContractMapper(), id);
    }

    @Override
    public void deleteOne(Integer id) {
        String sql = "DELETE  FROM contracts  WHERE id  = ?";
        delete(sql,id);
    }
}
