package sk.jurcisin.vincent;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import sk.jurcisin.vincent.model.Add;
import sk.jurcisin.vincent.model.Command;
import sk.jurcisin.vincent.model.DeleteAll;
import sk.jurcisin.vincent.model.PrintAll;
import sk.jurcisin.vincent.service.HibernateUtils;

public class ConsumerProducerTest {

    @Test
    void testProgram() throws InterruptedException {
        final List<Command> commands = List.of(
                new Add(1, "a1", "Robert"),
                new Add(2, "a2", "Martin"),
                new PrintAll(),
                new DeleteAll(),
                new PrintAll()
        );
        LinkedBlockingDeque<Command> fifoCommands = new LinkedBlockingDeque<>(commands);
        System.out.println("test");
        final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Runnable runnable = () -> {
            try {
                while (!fifoCommands.isEmpty()) {
                    final Command comm = fifoCommands.take();
                    comm.execute(sessionFactory);
                }
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        };

        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(runnable);
//        executorService.submit(runnable);
//        ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(2);
//        pool.schedule(runnable, 1, TimeUnit.SECONDS);
    }
}
