package my.example.runner;

import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;

/**
 * Runs queued tests (to be used with @RunWith)
 */
public class QueueRunnerSuite extends Suite {

    public QueueRunnerSuite(Class<?> klass) throws InitializationError {
        super(klass, new QueuedRunnerBuilder());
    }
}
