package sk.jurcisin.vincent;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.jupiter.api.Test;
import sk.jurcisin.vincent.api.Add;
import sk.jurcisin.vincent.api.Command;
import sk.jurcisin.vincent.api.DeleteAll;
import sk.jurcisin.vincent.api.PrintAll;
import sk.jurcisin.vincent.system.Consumer;

class ConsumerProducerAppTest {

    private final static BlockingQueue<Command> QUEUE = new LinkedBlockingQueue<>(
            List.of(
                    new Add(1, "a1", "Robert"),
                    new Add(2, "a2", "Martin"),
                    new PrintAll(),
                    new DeleteAll(),
                    new PrintAll()
            )
    );

    @Test
    void testingQueueNoThread() {
        Consumer c1 = new Consumer(QUEUE);
        c1.run();
    }
}
