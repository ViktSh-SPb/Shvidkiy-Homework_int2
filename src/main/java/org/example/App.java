package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.time.LocalDateTime;

/**
 * @author Viktor Shvidkiy
 */
public class App {
    public static void main(String[] args) {
        User user1 = new User("Alice", "alice@gmail.com", 20, LocalDateTime.now());
        User user2 = new User("John", "john@gmail.com", 25, LocalDateTime.now());
        saveUser(user1);
        saveUser(user2);

        User loaded = getUser(user1.getId());
        System.out.println("Read: " + loaded);

        loaded.setName("Sara");
        updateUser(loaded);
        System.out.println("Update: " + loaded);

        deleteUser(loaded.getId());
        System.out.println("Delete: "+ getUser(loaded.getId()));

    }

    public static void saveUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
        }
    }

    public static User getUser(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(User.class, id);
        }
    }

    public static void updateUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(user);
            tx.commit();
        }
    }

    public static void deleteUser(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.remove(user);
            }
            tx.commit();
        }
    }
}
