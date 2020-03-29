package com.sasha.dataAccess;

import com.sasha.entity.bets.Bet;
import com.sasha.entity.bets.DisplayedBet;
import com.sasha.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class BetRepositoryImpl<T extends Bet> implements BetRepository<T> {
    @Override
    public void create(T t) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.saveOrUpdate(t);
            session.getTransaction().commit();
        }

    }

    @Override
    public T findById(Integer id) {
        return null;
    }

    @Override
    public List<T> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()){
            // Create CriteriaBuilder
            CriteriaBuilder builder = session.getCriteriaBuilder();

            // Create CriteriaQuery
            CriteriaQuery<Bet> criteria = builder.createQuery(Bet.class);

            // Specify criteria root
            criteria.from(Bet.class);

            // Execute query
            List<Bet> entityList = session.createQuery(criteria).getResultList();

            return (List<T>) entityList;
        }
    }

    @Override
    public void update(T t) {

    }
}
