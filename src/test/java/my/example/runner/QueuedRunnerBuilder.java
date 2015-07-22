package my.example.runner;

import my.example.annotation.Queued;
import org.junit.runner.Runner;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.RunnerBuilder;

/**
 * Created by peere on 7/22/2015.
 */
public class QueuedRunnerBuilder extends RunnerBuilder {

    @Override
    public Runner runnerForClass(Class<?> testClass) throws Throwable {
        // Check for queuedAnnotation example
        Queued queuedAnnotation = testClass.getAnnotation(Queued.class);
        if (queuedAnnotation != null) {
            return new QueueRunner(testClass);
        }
        else {
            return new BlockJUnit4ClassRunner(testClass);
        }
    }
}
