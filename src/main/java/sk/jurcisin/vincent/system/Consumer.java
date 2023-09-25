package sk.jurcisin.vincent.system;

import java.util.concurrent.BlockingQueue;
import sk.jurcisin.vincent.api.Command;

public class Consumer implements Runnable {

    private final BlockingQueue<Command> queue;

    public Consumer(BlockingQueue<Command> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!queue.isEmpty()) {
                final Command command = queue.take();
                command.execute();
            }
        } catch (Exception ex) {
            System.out.println("Thread exiting");
        }
    }
}
