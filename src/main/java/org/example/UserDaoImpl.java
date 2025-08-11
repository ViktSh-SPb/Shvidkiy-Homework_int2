package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

/**
 * @author Viktor Shvidkiy
 */
public class UserDaoImpl implements UserDao{
    private static final Logger logger = LogManager.getLogger(UserDao.class);

    @Override
    public void save(User user) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            tx= session.beginTransaction();
            session.persist(user);
            tx.commit();
            logger.info("Запись сохранена: {}", user);
        }catch (Exception e){
            if(tx!=null){
                tx.rollback();
                logger.warn("Произошла ошибка. Транзакция отменена.");
            }
            logger.error("Ошибка при сохранении записи: {}", e.getMessage());
            throw new RuntimeException("Не могу сохранить запись.", e);
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
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            tx= session.beginTransaction();
            session.merge(user);
            tx.commit();
        } catch (Exception e){
            if (tx!=null)tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            tx= session.beginTransaction();
            session.remove(user);
            tx.commit();
        }catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
    }
}
