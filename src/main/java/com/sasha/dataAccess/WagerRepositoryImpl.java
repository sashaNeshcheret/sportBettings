package com.sasha.dataAccess;

import com.sasha.entity.users.User;
import com.sasha.entity.wagers.Wager;
import com.sasha.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class WagerRepositoryImpl<T extends Wager> implements WagerRepository<T>{
    @Override
    public void create(T wager) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(wager);
            session.getTransaction().commit();
        }
    }

    @Override
    public T findById(Integer id) {
        return null;
    }

    @Override
    public List<T> findByUser(User user) {
        return new ArrayList<>();
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>();
    }

    @Override
    public List<T> findWinner(){
        return new ArrayList<>();
    }

    @Override
    public void update(Wager wager) {

    }
}
