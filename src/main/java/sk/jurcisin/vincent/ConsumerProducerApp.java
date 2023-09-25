package sk.jurcisin.vincent;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import sk.jurcisin.vincent.api.Add;
import sk.jurcisin.vincent.api.Command;
import sk.jurcisin.vincent.api.DeleteAll;
import sk.jurcisin.vincent.api.PrintAll;
import sk.jurcisin.vincent.system.Consumer;

public class ConsumerProducerApp {

    public static void main(String[] args) {
        BlockingQueue<Command> producerConsumerQueue = new LinkedBlockingQueue<>(
                List.of(
                        new Add(1, "a1", "Robert"),
                        new Add(2, "a2", "Martin"),
                        new PrintAll(),
                        new DeleteAll(),
                        new PrintAll()
                )
        );

        Consumer c1 = new Consumer(producerConsumerQueue);
        Consumer c2 = new Consumer(producerConsumerQueue);

        new Thread(c1).start();
        new Thread(c2).start();
    }
}
