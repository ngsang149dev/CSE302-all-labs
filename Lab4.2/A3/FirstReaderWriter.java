package A3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FirstReaderWriter {
    private int readerCount;
    private int writerCount;
    private Lock lock = new ReentrantLock();
    private Condition readerCondition = lock.newCondition();
    private Condition writerCondition = lock.newCondition();

    public void readerEnter() {
        lock.lock();
        try {
            while (writerCount == 1) {
                readerCondition.await();
            }
            readerCount++;
        } catch(InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void readerExit() {
        lock.lock();
        try {
            readerCount--;
            if (readerCount == 0) {
                writerCondition.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void writerEnter() {
        lock.lock();
        try {
            while (writerCount == 1 || readerCount > 0) {
                writerCondition.await();
            }
            writerCount = 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void writerExit() {
        lock.lock();
        try {
            writerCount = 0;
            readerCondition.signalAll();
            writerCondition.signal();
        } finally {
            lock.unlock();
        }
    }
}
