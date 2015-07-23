package my.example.test;

import my.example.queue.QueueRunner;
import org.junit.runner.RunWith;

/**
 * Base class for running tests on limited resources
 */
@RunWith(QueueRunner.class)
public abstract class BaseTest {
    protected final long SLEEP_TIME = 1000; // Sleep for 1 sec
}
