package sk.jurcisin.vincent.model;

import org.hibernate.SessionFactory;

public class DeleteAll implements Command {

    public void execute(SessionFactory sessionFactory) {
        System.out.println("Executing command: DeleteAll");

    }
}
