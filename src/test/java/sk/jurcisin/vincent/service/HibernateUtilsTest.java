package sk.jurcisin.vincent.service;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;
import sk.jurcisin.vincent.model.User;

class HibernateUtilsTest {

    @Test
    void hibernateConfigTest() {
        User robert = new User(1, "a1", "Robert");
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction trx = session.beginTransaction();
            // persisting entity
            session.persist(robert);
            session.persist(new User(2, "a2", "John"));
            trx.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new IllegalArgumentException(e);
        }

        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            List<User> users = session.createQuery("from User", User.class).list();
            users.forEach(user -> System.out.println(user.toString()));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new IllegalArgumentException(e);
        }
    }
}
