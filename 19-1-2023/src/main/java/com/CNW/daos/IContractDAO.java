package com.CNW.daos;

import com.CNW.models.ContractModel;

import java.util.List;

public interface IContractDAO extends GenericDAO<ContractModel> {
    List<ContractModel> getAll();
    void updateContract(Integer id,ContractModel model);

    void cancelContract(Integer id);

    ContractModel getOne(Integer id);

    void createOne(ContractModel model);

    List<ContractModel> getAllWithUserId(Integer id);

    void deleteOne(Integer id);
}
