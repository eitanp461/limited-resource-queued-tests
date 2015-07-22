package my.example.runner;

import my.example.annotation.Queued;
import my.example.queue.ResourceAwareTestQueue;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.TestClass;

/**
 * Created by peere on 7/22/2015.
 */
public class QueueRunner extends BlockJUnit4ClassRunner {

    /**
     * Creates a BlockJUnit4ClassRunner to run {@code klass}
     *
     * @param klass
     * @throws org.junit.runners.model.InitializationError if the example class is malformed.
     */
    public QueueRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    public void run(final RunNotifier notifier) {
        if (isQueued(getTestClass())) {
            System.out.println("Running queued test " + getTestClass().getName());
            ResourceAwareTestQueue.getInstance().execute(new TestExecutor() {
                @Override
                public void execute() {
                    QueueRunner.super.run(notifier);
                }
            });
        }
        else {
            System.out.println("Running standard test " + getTestClass().getName());
            super.run(notifier);
        }
    }

    private boolean isQueued(TestClass testClass) {
        // Check for queuedAnnotation example
        Queued queuedAnnotation = testClass.getAnnotation(Queued.class);
        return queuedAnnotation != null;
    }
}
