package com.sasha.dataAccess;

public interface UserRepository<T> extends Repository<T> {

    T findById(String name);

}
