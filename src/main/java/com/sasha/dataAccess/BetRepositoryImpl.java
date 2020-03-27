package com.sasha.dataAccess;

import com.sasha.entity.bets.Bet;

import java.util.List;

public class BetRepositoryImpl<T extends Bet> implements BetRepository<T> {
    @Override
    public void create(T t) {
        System.out.println("Bets creation simulation");
    }

    @Override
    public T findById(Integer id) {
        return null;
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public void update(T t) {

    }
}
