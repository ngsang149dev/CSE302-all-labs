package A2;

public class WorkingThread extends Thread {
    private MyThreadPool pool;

    public WorkingThread(MyThreadPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        while (true) {
            Runnable task;

            try {
                task = pool.getTask();
            } catch (InterruptedException e) {
                return;
            }

            if (task == null) {
                return;
            }

            try {
                task.run();
            } catch (RuntimeException e) {
            }
        }
    }
}
