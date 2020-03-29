package com.sasha.dataAccess;

import com.sasha.util.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.List;

public interface Repository<T> {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    void create(T t);

    T findById(Integer id);

    List<T> findAll();

    void update(T t);
}
