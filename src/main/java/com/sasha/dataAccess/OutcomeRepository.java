package com.sasha.dataAccess;

import com.sasha.entity.bets.Outcome;
import com.sasha.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class OutcomeRepository<T extends Outcome> implements Repository<T>{

    @Override
    public void create(T t) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();

            session.save(t);

            session.getTransaction().commit();
        }

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
