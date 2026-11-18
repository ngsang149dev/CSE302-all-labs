package A2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyThreadPool {
    private List<WorkingThread> workingThreads = new ArrayList<>();
    private Queue<Runnable> tasks = new LinkedList<>();  
    private Lock lock = new ReentrantLock();
    private Condition taskAvailable = lock.newCondition();
    private boolean shutdown = false;

    public MyThreadPool() {
        this(Runtime.getRuntime().availableProcessors());
    }

    public MyThreadPool(int size) {
        for (int i = 1; i <= size; i++) {
            WorkingThread worker = new WorkingThread(this);
            worker.setName("worker-" + i);
            workingThreads.add(worker);
        }

        for (WorkingThread worker : workingThreads) {
            worker.start();
        }
    }

    public void add(Runnable task) {
        lock.lock();
        try {
            tasks.offer(task);     
            taskAvailable.signal();    
        } finally {
            lock.unlock();
        }
    }

    public Runnable getTask() throws InterruptedException {
        lock.lock();
        try {
            while (tasks.isEmpty() && !shutdown) {
                taskAvailable.await();
            }

            // return null means pool is shutdown
            if (shutdown) {
                return null;
            }

            return tasks.poll();   
        } finally {
            lock.unlock();
        }
    }

    public void shutdown() {
        lock.lock();
        try {
            shutdown = true;
            taskAvailable.signalAll(); 
        } finally {
            lock.unlock();
        }

        for (WorkingThread worker : workingThreads) {
            worker.interrupt();
        }

        for (WorkingThread worker : workingThreads) {
            if (worker == Thread.currentThread()) {
                continue;          
            }

            try {
                worker.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
