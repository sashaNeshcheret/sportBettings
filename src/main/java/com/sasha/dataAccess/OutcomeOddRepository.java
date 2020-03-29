package com.sasha.dataAccess;

import com.sasha.entity.bets.OutcomeOdd;

import java.util.List;

public class OutcomeOddRepository<T extends OutcomeOdd> implements Repository<T> {

    @Override
    public void create(T t) {

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
