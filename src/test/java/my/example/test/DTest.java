package my.example.test;

import my.example.annotation.Queued;
import org.junit.Assert;
import org.junit.Test;

@Queued
public class DTest extends BaseTest {

    @Test
    public void testQueue() throws InterruptedException {
        Thread.sleep(SLEEP_TIME);
        Assert.assertTrue(true);
    }
}
