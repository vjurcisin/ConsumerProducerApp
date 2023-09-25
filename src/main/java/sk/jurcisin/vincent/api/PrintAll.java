package sk.jurcisin.vincent.api;

import java.util.List;
import org.hibernate.Session;
import sk.jurcisin.vincent.model.User;
import sk.jurcisin.vincent.service.HibernateUtils;

public class PrintAll implements Command {

    @Override
    public void execute() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            List<User> users = session.createQuery("from User", User.class).list();
            users.forEach(user -> System.out.println(user.toString()));
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }
}
