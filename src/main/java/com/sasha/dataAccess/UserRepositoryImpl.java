package com.sasha.dataAccess;

import com.sasha.entity.users.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl<T extends User> implements UserRepository<T>{

    @Override
    public void create(User user) {

    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public T findById(Integer id) {
        return null;
    }

    @Override
    public T findByName(String name) {
        return null;
    }

    @Override
    public void update(T user) {

    }
}
