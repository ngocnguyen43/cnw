package com.CNW.daos;

import com.CNW.utils.mapper.IMapper;

import java.util.List;

public interface GenericDAO<T> {
    <T> List<T> query(String sql, IMapper<T> mapper, Object... parameters);

    void update(String sql, Object... params);

    void insert(String sql, Object... params);

    void delete(String sql, Object... params);
}
