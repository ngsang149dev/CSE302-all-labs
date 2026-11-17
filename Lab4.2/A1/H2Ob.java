package A1;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class H2Ob {

    private Lock lock = new ReentrantLock();
    List<Condition> hydroQueue = new LinkedList<>();
    List<Condition> oxyQueue = new LinkedList<>();

    public void hydro() {
        lock.lock();
        try {
            Condition hydroCondition = lock.newCondition();
            hydroQueue.add(hydroCondition);
            if (hydroQueue.size() >= 2 && oxyQueue.size() >= 1) {
                Condition h1 = hydroQueue.remove(0);
                Condition h2 = hydroQueue.remove(0);
                Condition o1 = oxyQueue.remove(0);

                h1.signal();
                h2.signal();
                o1.signal();
            } 

            while (hydroQueue.contains(hydroCondition)) {
                hydroCondition.await();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public void oxy() {
        lock.lock();
        try {
            Condition oxyCondition = lock.newCondition();
            oxyQueue.add(oxyCondition);

            if (hydroQueue.size() >= 2 && oxyQueue.size() >= 1) {
                Condition h1 = hydroQueue.remove(0);
                Condition h2 = hydroQueue.remove(0);
                Condition o1 = oxyQueue.remove(0);

                h1.signal();
                h2.signal();
                o1.signal();
            }  

            while (oxyQueue.contains(oxyCondition)) {
                oxyCondition.await();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }
}