package com.CNW.utils.mapper;

import java.sql.ResultSet;


public interface IMapper<T> {
    T mapRow(ResultSet result);

}
