package org.example;

import java.time.LocalDateTime;

/**
 * @author Viktor Shvidkiy
 */
public class App {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        User user1 = new User("Alice", "alice@gmail.com", 20, LocalDateTime.now());
        User user2 = new User("John", "john@gmail.com", 25, LocalDateTime.now());
        userDao.save(user1);
        userDao.save(user2);

        User loaded = userDao.getById(user1.getId());
        System.out.println("Read: " + loaded);

        loaded.setAge(21);
        userDao.update(loaded);
        System.out.println("Update: " + loaded);

        userDao.delete(loaded);
        System.out.println("Delete: "+ userDao.getById(loaded.getId()));

        for(User user:userDao.getAll()){
            System.out.println(user);
        }

    }
}
