package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static final UserDaoHibernateImpl USER_DAO_HIBERNATE_IMPL = new UserDaoHibernateImpl();
    @Override
    public final void createUsersTable() {
        USER_DAO_HIBERNATE_IMPL.createUsersTable();
    }

    @Override
    public final void dropUsersTable() {
        USER_DAO_HIBERNATE_IMPL.dropUsersTable();
    }

    @Override
    public final void saveUser(String name, String lastName, byte age) {
        USER_DAO_HIBERNATE_IMPL.saveUser(name, lastName, age);
    }

    @Override
    public final void removeUserById(long id) {
        USER_DAO_HIBERNATE_IMPL.removeUserById(id);
    }

    @Override
    public final List<User> getAllUsers() {
        return USER_DAO_HIBERNATE_IMPL.getAllUsers();
    }

    @Override
    public final void cleanUsersTable() {
        USER_DAO_HIBERNATE_IMPL.cleanUsersTable();
    }
}
