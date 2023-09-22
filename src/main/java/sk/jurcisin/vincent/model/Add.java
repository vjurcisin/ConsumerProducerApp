package sk.jurcisin.vincent.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Add implements Command {

    private final Integer userId;

    private final String userGuid;

    private final String userName;

    public Add(Integer userId, String userGuid, String userName) {
        this.userId = userId;
        this.userGuid = userGuid;
        this.userName = userName;
    }

    public void execute(SessionFactory sessionFactory) {
        System.out.println("Executing command: Add");

        Transaction tx = null;
        try (Session sess = sessionFactory.openSession()) {
            tx = sess.beginTransaction();
            User u = new User(userId, userGuid, userName);
            sess.persist(u);
            tx.commit();
        } catch (Exception e) {
            System.err.println("Error: " + e);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    @Override
    public String toString() {
        return "AddCommand{" +
                "userId=" + userId +
                ", userGuid=" + userGuid +
                ", userName='" + userName + '\'' +
                '}';
    }
}
