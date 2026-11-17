package A3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TSQueue {
    private final Queue<Integer> queue = new LinkedList<>();
    private final Lock lock = new ReentrantLock();

    public int addLast(int value) {
        lock.lock();
        try {
            queue.add(value);
            return value;
        } finally {
            lock.unlock();
        }
    }

    public int removeFirst() {
        lock.lock();
        try {
            return queue.remove();
        } finally {
            lock.unlock();
        }
    }
}
