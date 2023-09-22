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
        Transaction trx = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            // new trx
            trx = session.beginTransaction();
            // persisting entity
            session.persist(robert);
            session.persist(new User(2, "a2", "John"));
            trx.commit();
        } catch (Exception e) {
            if (trx != null) {
                trx.rollback();
            }
            e.printStackTrace();
        }

        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            List<User> users = session.createQuery("from User", User.class).list();
            users.forEach(user -> System.out.println(user.toString()));
        } catch (Exception e) {
            if (trx != null) {
                trx.rollback();
            }
            e.printStackTrace();
        }
    }
}
