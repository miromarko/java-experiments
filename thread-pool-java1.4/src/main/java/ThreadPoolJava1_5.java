import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolJava1_5 {

	public static void main(String[] args) {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 20; i++)
			fixedThreadPool.execute(ThreadPoolTest.createTask(i));

		fixedThreadPool.shutdown();

	}

}
