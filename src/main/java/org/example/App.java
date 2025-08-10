package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        User user = new User("Alice", "Alice@gmail.com", 20, LocalDateTime.now());
        saveUser(user);

        User loaded;
    }

    public static void saveUser(User user){
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction tx = session.beginTransaction();
            session.merge(user);
            tx.commit();
        }
    }

    public static User getUser(Long id){
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.get(User.class, id);
        }
    }

    public static void updateUser(User user){
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            Transaction tx = session.beginTransaction();
            session.merge(user);
            tx.commit();
        }
    }

    public static void deleteUser(Long id){
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction tx = session.beginTransaction();
            User user = session.get(User.class, id);
            if(user!=null){
                session.remove(user);
            }
            tx.commit();
        }
    }
}
