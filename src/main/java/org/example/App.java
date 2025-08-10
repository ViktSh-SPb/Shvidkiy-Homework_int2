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
        System.out.println( "Hello World!" );
    }

    public static void saveUser(User user){
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction tx = session.beginTransaction();
            session.merge(user);
            tx.commit();
        }
    }
}
