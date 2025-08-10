package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

/**
 * @author Viktor Shvidkiy
 */
public class UserDaoImpl implements UserDao{

    @Override
    public void save(User user) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            tx= session.beginTransaction();
            session.persist(user);
            tx.commit();
        }catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public User getById(Long id) {
        try(Session session =HibernateUtil.getSessionFactory().openSession()){
            return session.get(User.class, id);
        }
    }

    @Override
    public List<User> getAll() {
        try (Session session=HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from User", User.class).list();
        }
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }
}
