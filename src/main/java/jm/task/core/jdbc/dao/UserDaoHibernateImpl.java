package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

public class UserDaoHibernateImpl extends Util implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoHibernateImpl.class.getName());
    private static EntityManagerFactory managerFactory = getEntityManagerFactory(getProperties());

    @Override
    public void createUsersTable() {
        try {
            EntityManager manager = managerFactory.createEntityManager();
            manager.getTransaction().begin();
            String sql = "CREATE TABLE IF NOT EXISTS users(" +
                    "id INT NOT NULL AUTO_INCREMENT, " +
                    "name TINYTEXT NOT NULL, " +
                    "last_name TINYTEXT NOT NULL, " +
                    "age INT NOT NULL, " +
                    "PRIMARY KEY(id))";
            javax.persistence.Query query = manager.createNativeQuery(sql);
            query.executeUpdate();
            manager.getTransaction().commit();
            manager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void dropUsersTable() {
        try {
            EntityManager manager = managerFactory.createEntityManager();
            manager.getTransaction().begin();
            javax.persistence.Query query = manager.createNativeQuery("DROP TABLE IF EXISTS users");
            query.executeUpdate();
            manager.getTransaction().commit();
            manager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            EntityManager manager = managerFactory.createEntityManager();
            manager.getTransaction().begin();
            User user = new User(name, lastName, age);
            manager.persist(user);
            manager.getTransaction().commit();
            manager.close();
            String formatMessage = String.format("*** User with name %s add in Data Base ***", name);
            LOGGER.info(formatMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            EntityManager manager = managerFactory.createEntityManager();
            manager.getTransaction().begin();
            User user = new User();
            user.setId(id);
            manager.remove(user);
            manager.getTransaction().commit();
            manager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            EntityManager manager = managerFactory.createEntityManager();
            TypedQuery<User> query = manager.createQuery("from users t", User.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public void cleanUsersTable() {
        try {
            EntityManager manager = managerFactory.createEntityManager();
            manager.getTransaction().begin();
            javax.persistence.Query query = manager.createNativeQuery("DELETE FROM users");
            query.executeUpdate();
            manager.getTransaction().commit();
            manager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
