package com.sasha.dataAccess;

import com.sasha.entity.users.User;
import com.sasha.entity.wagers.Wager;

import java.util.ArrayList;
import java.util.List;

public class WagerRepositoryImpl implements WagerRepository{
    @Override
    public void createWager(Wager wager) {

    }

    @Override
    public List<Wager> findByUser(User user) {
        return new ArrayList<>();
    }

    @Override
    public List<Wager> findAll() {
        return new ArrayList<>();
    }

    @Override
    public List<Wager> findWinner(){
        return new ArrayList<>();
    }

    @Override
    public void update(Wager wager) {

    }
}
