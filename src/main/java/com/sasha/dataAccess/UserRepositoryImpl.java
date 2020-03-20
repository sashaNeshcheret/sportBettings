package com.sasha.dataAccess;

import com.sasha.entity.users.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{
    @Override
    public void createUser(User user) {

    }

    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public User findByName(String name) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }
}
