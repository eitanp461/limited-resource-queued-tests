package my.example.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * A queue for tests using a limited resource.
 * Tests are read from the queue and executed by threads where each thread has an exclusive access to an instance
 * of the limited resource
 */
public class ResourceAwareTestQueue {

    private static final ResourceAwareTestQueue instance = new ResourceAwareTestQueue();

    private BlockingQueue<TestExecutor> queue;

    private ResourceAwareTestQueue() {
        try {
            int workItem = 0;
            // Create the queue
            queue = new LinkedTransferQueue<TestExecutor>();

            // Create the child worker threads
            Integer threadCount = Integer.parseInt(System.getProperty("threadCount", "1"));
            System.out.println("Creating " + threadCount + " threads");
            for (int i = 0; i < threadCount; i++) {
                FileWriterTestExecutor worker = new FileWriterTestExecutor(queue);
                worker.start();
            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public static ResourceAwareTestQueue getInstance() {
        return instance;
    }

    public void execute(TestExecutor testExecutor) {
        try {
            queue.put(testExecutor);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (testExecutor) {
            try {
                testExecutor.wait();
            } catch (InterruptedException e) {
                // Test finished
                e.printStackTrace();
            }

        }
    }
}