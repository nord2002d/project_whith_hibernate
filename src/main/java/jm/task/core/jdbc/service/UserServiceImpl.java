package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl extends UserDaoHibernateImpl implements UserService {
    @Override
    public final void createUsersTable() {
        super.createUsersTable();
    }

    @Override
    public final void dropUsersTable() {
        super.dropUsersTable();
    }

    @Override
    public final void saveUser(String name, String lastName, byte age) {
        super.saveUser(name, lastName, age);
    }

    @Override
    public final void removeUserById(long id) {
        super.removeUserById(id);
    }

    @Override
    public final List<User> getAllUsers() {
        return super.getAllUsers();
    }

    @Override
    public final void cleanUsersTable() {
        super.cleanUsersTable();
    }
}
