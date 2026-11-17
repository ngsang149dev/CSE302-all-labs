package A4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SecondReaderWriter {
    private int readerCount;
    private boolean isWriting;
    private int waitingWriters;
    private Lock lock = new ReentrantLock();
    private Condition readerCondition = lock.newCondition();
    private Condition writerCondition = lock.newCondition();

    public void readerEnter() {
        lock.lock();
        try {
            while (isWriting == true || waitingWriters > 0) {
                readerCondition.await();
            }
            readerCount++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void readerExit() {
        lock.lock();
        try {
            readerCount--;
            if (readerCount == 0 && waitingWriters > 0) {
                writerCondition.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void writerEnter() {
        lock.lock();
        try {
            waitingWriters++;
            try {
                while (isWriting == true || readerCount > 0) {
                    writerCondition.await();
                }
                isWriting = true;
            } finally {
                waitingWriters--;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void writerExit() {
        lock.lock();
        try {
            isWriting = false;
            if (waitingWriters > 0) {
                writerCondition.signal();
            } else {
                readerCondition.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }
}
