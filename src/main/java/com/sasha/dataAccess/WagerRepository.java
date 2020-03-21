package com.sasha.dataAccess;

import com.sasha.entity.users.User;
import com.sasha.entity.wagers.Wager;

import java.util.List;

public interface WagerRepository<T> extends Repository<T> {

    List<T> findByUser(User user);

    List<T> findWinner();

}
