package sk.jurcisin.vincent.model;

import org.hibernate.SessionFactory;

public interface Command {

    void execute(SessionFactory sessionFactory);
}
