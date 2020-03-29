package com.sasha.dataAccess;

import com.sasha.entity.users.User;

public interface UserRepository<T extends User> extends Repository<T> {

    T findById(String name);

}
