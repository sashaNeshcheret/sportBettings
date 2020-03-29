package com.sasha.dataAccess;

import com.sasha.entity.users.Player;
import com.sasha.entity.users.User;
import com.sasha.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class UserRepositoryImpl<T extends User> implements UserRepository<T> {

    @Override
    public void create(User user) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();

//            session.persist(user);
            Serializable savedUser = session.save(user);
            System.out.println("user id - " + savedUser);

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
    public T findById(String name) {
        User user = null;
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {

//            session.beginTransaction();

            user = session.get(Player.class, name);


//            session.getTransaction().commit();

        }
        return (T) user;
    }


//    @Override
//    public T findByName(String name) {
//        T user = (User) new Player();
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        try(Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//
//            StringExpression format = StringFormatter.format("SELECT * FROM user WHERE name = $s", name);
//            NativeQuery sqlQuery = session.createSQLQuery(format.getValue());
//            List<Object[]> rows = sqlQuery.list();
//            for(Object[] row : rows){
//                user.setName(row[1].toString());
//                user.setBalance(new BigDecimal(Double.parseDouble(row[2].toString())));
//            }
//
//            session.getTransaction().commit();
//
//        }
//        return user;
//    }

    @Override
    public void update(T user) {

    }
}
