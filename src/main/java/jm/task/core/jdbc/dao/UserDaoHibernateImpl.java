package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.logging.Logger;

public class UserDaoHibernateImpl implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoHibernateImpl.class.getName());


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSession(Util.getProperties())) {
            session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS users(" +
                    "id INT NOT NULL AUTO_INCREMENT, " +
                    "name TINYTEXT NOT NULL, " +
                    "last_name TINYTEXT NOT NULL, " +
                    "age INT NOT NULL, " +
                    "PRIMARY KEY(id))";
            Query<? extends User> query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSession(Util.getProperties())) {
            session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS users";
            Query<? extends User> query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSession(Util.getProperties())) {
            Transaction transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
            String formatMessage = String.format("*** User with name %s add in Data Base ***", name);
            LOGGER.info(formatMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSession(Util.getProperties())) {
            Transaction transaction = session.beginTransaction();
            User user = new User();
            user.setId(id);
            session.remove(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSession(Util.getProperties())) {
            Query<User> query = session.createQuery("from users", User.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSession(Util.getProperties())) {
            session.beginTransaction();
            String sql = "DELETE FROM users";
            Query<? extends User> query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
