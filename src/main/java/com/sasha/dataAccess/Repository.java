package com.sasha.dataAccess;

import java.util.List;

public interface Repository<T> {

    void create(T t);

    T findById(Integer id);

    List<T> findAll();

    void update(T t);
}
