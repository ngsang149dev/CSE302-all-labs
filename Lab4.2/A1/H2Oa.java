package A1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class H2Oa {
    private int hydroCount = 0;
    private int oxyCount = 0;

    private Lock lock = new ReentrantLock();
    private Condition oxyCondition = lock.newCondition();
    private Condition hydroCondition = lock.newCondition();

    public void hydro() {
        lock.lock();
        try {
            hydroCount++;
            if (hydroCount < 2 || oxyCount < 1) {
                hydroCondition.await();
            } else {
                hydroCount -= 2;
                oxyCount -= 1;

                hydroCondition.signal();
                oxyCondition.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void oxy() {
        lock.lock();
        try {
            oxyCount++;
            if (hydroCount< 2 || oxyCount < 1) {
                oxyCondition.await();
            } else {
                hydroCount -= 2;
                oxyCount -= 1;

                hydroCondition.signal();
                hydroCondition.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}