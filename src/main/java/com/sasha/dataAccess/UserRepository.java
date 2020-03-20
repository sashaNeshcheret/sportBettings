package com.sasha.dataAccess;

import com.sasha.entity.users.User;
import org.springframework.util.StringUtils;

public interface UserRepository {

    void createUser(User user);

    User findById(Integer id);

    User findByName(String name);

    User update(User user);

}
