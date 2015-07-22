package my.example.queue;

import my.example.runner.TestExecutor;

import java.util.concurrent.BlockingQueue;

/**
 * A thread that is running a example exclusively on a new instance of the limited resource
 */
public abstract class ResourceAwareTestExecutor extends Thread {

    private final BlockingQueue<TestExecutor> queue;

    public ResourceAwareTestExecutor(BlockingQueue<TestExecutor> queue) {
        this.queue = queue;
    }

    public void run() {
        createResource();
        try {
            while (true) {
                TestExecutor testExecutor = queue.take();
                runTest(testExecutor);
            }
        } catch (InterruptedException ie) {
            // just terminate
        }
    }

    /**
     * Throws?
     */
    public void runTest(TestExecutor testExecutor) {
        beforeTest();
        testExecutor.execute();
        afterTest();
        synchronized (testExecutor) {
            // Tell the queue that this example has finished
            testExecutor.notify();
        }
    }

    protected void beforeTest() {
        // Do nothing by default, to be overridden by syb classes that have something to do here
    }

    protected void afterTest() {
        // Do nothing by default, to be overridden by syb classes that have something to do here
    }

    /**
     * Create this thread's instance of the limited resource
     */
    protected abstract void createResource();
}