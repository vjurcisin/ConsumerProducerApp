package sk.jurcisin.vincent.api;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sk.jurcisin.vincent.service.HibernateUtils;

public class DeleteAll implements Command {

    @Override
    public void execute() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction trx = session.beginTransaction();
            final Query deleteFromUser = session.createQuery("delete from User");
            deleteFromUser.executeUpdate();
            trx.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
