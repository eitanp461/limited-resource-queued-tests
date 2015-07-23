package my.example.queue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.BlockingQueue;

/**
 * Created by peere on 7/22/2015.
 */
public class FileWriterTestExecutor extends ResourceAwareTestExecutor {

    private File limitedResource;

    public FileWriterTestExecutor(BlockingQueue<TestExecutor> queue) {
        super(queue);
    }

    @Override
    protected void createResource() {
        String prefix = Thread.currentThread().getName() + "-" + System.currentTimeMillis();
        try {
            limitedResource = File.createTempFile(prefix, null);
            System.out.println("created file " + limitedResource.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void beforeTest() {
        // Write to file
        try {
            String msg = Thread.currentThread().getName() + "-" + System.currentTimeMillis();
            System.out.println("Writing " + msg + " to " + limitedResource.getName());
            Files.write(Paths.get(limitedResource.toURI()), (msg + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Failed to print log to file " + limitedResource.getName(), e);
        }
    }
}
