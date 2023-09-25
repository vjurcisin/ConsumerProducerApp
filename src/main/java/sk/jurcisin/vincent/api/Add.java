package sk.jurcisin.vincent.api;

import org.hibernate.Session;
import org.hibernate.Transaction;
import sk.jurcisin.vincent.model.User;
import sk.jurcisin.vincent.service.HibernateUtils;

public class Add implements Command {

    private final Integer userId;

    private final String userGuid;

    private final String userName;

    public Add(Integer userId, String userGuid, String userName) {
        this.userId = userId;
        this.userGuid = userGuid;
        this.userName = userName;
    }

    @Override
    public void execute() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction tx;
            tx = session.beginTransaction();
            User user = new User(userId, userGuid, userName);
            session.persist(user);
            tx.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
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
