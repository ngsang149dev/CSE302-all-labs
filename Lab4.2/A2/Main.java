package A2;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyThreadPool pool = new MyThreadPool(3);

        for (int i = 1; i <= 10; i++) {
            int taskId = i;

            pool.add(() -> {
                System.out.println(Thread.currentThread().getName()
                        + " running task " + taskId);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName()
                            + " task " + taskId + " has shutdown");
                    Thread.currentThread().interrupt();
                    return;
                }

                System.out.println(Thread.currentThread().getName()
                        + " done task " + taskId);
            });
        }
        Thread.sleep(10000);

        pool.shutdown();
        System.out.println("Thread pool has shutdown");
    }
}
