package com.sasha.dataAccess;

import com.sasha.entity.users.User;
import com.sasha.entity.wagers.Wager;

import java.util.List;

public interface WagerRepository {

    void createWager(Wager wager);

    List<Wager> findByUser(User user);

    List<Wager> findAll();

    List<Wager> findWinner();

    void update(Wager wager);
}
