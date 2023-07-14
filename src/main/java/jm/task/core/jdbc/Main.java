package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        UserServiceImpl userDao = new UserServiceImpl();
        userDao.dropUsersTable();
        userDao.createUsersTable();
        userDao.saveUser("A", "A1", (byte) 11);
        userDao.saveUser("B", "B2", (byte) 22);
        userDao.saveUser("C", "C3", (byte) 33);
        userDao.saveUser("D", "D4", (byte) 44);
        userDao.getAllUsers();
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();

    }
}
