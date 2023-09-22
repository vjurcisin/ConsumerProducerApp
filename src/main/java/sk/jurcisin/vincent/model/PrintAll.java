package sk.jurcisin.vincent.model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PrintAll implements Command {

    public void execute(SessionFactory sessionFactory) {
        System.out.println("Executing command: PrintAll");

        try (Session session = sessionFactory.openSession()) {
            List<User> users = session.createQuery("from User", User.class).list();
            users.forEach(user -> System.out.println(user.toString()));
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }
}
