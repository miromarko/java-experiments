public class ThreadPoolTest {

	public static void main(String[] args) {
		// test
		int numTasks = 10;
		int numThreads = 2;

		// create the thread pool
		ThreadPool threadPool = new ThreadPool(numThreads);

		// run example tasks
		for (int i = 0; i < numTasks; i++) {
			threadPool.runTask(createTask(i));
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("--------------------");
		// run example tasks
		for (int i = 0; i < numTasks; i++) {
			threadPool.runTask(createTask(i));
		}
		// close the pool and wait for all tasks to finish.
		threadPool.closeAll();
	}

	/**
	 * Creates a simple Runnable that prints an ID, waits 500 milliseconds, then
	 * prints the ID again.
	 */
	public static Runnable createTask(final int taskID) {
		return new Runnable() {
			public void run() {
				System.out.println("Task " + taskID + ": start");

				// simulate a long-running task
				try {
					Thread.sleep((long) (Math.random() % 200) + 300);
				} catch (InterruptedException ex) {
				}

				System.out.println("Task " + taskID + ": end");
			}
		};
	}

}
