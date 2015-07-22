# limited-resource-queued-tests
Running JUnit tests that need exclusive use of a limited resource concurrently using maven-failsafe-plugin

Running tests concurrently can cause test flakiness when some tests change the system state in a way that impacts other tests running in the same time.

For example, if several tests are running on an application tenant and one test changes the application's metadata then it affects other tests running in the same time.

When the shared resource is easy to create then each test creates its own instance of that resource.

When it's a costly resource, such as an application tenant which creates database schemas, then only a few instances are created beforehand.

In this case such tests run serially with an exclusive use of the limited resource so they do not impact each other.

The repository shows a solution that creates instances of the limited resource dynamically and runs tests exclusively and concurrently.

The limited resource here is a file, which logs test execution.

Tests are queued and run concurrently, however each test runs with an exclusive use of the file.

The output files are created in java.io.tmp folder.

Run

		mvn clean install
		
And check that tests run multi-threaded where each thread is executing tests serially and provides exclusive access to the shared file.

 
