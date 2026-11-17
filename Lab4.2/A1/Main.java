package A1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        runA();
        runB();
    }

    private static void runA() throws InterruptedException {
        H2Oa h2o = new H2Oa();

        int oxyNumber = 100;
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < oxyNumber; i++) {
            threads.add(new HydroThread(h2o));
            threads.add(new HydroThread(h2o));
            threads.add(new OxyThread(h2o));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
        System.out.println("Cau a: Done");
    }

    private static void runB() throws InterruptedException {
        H2Ob h2o = new H2Ob();

        int oxyNumber = 100;
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < oxyNumber; i++) {
            threads.add(new HydroThread(h2o));
            threads.add(new HydroThread(h2o));
            threads.add(new OxyThread(h2o));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
        System.out.println("Cau b: Done");
    }
}

class HydroThread extends Thread {
    private final Runnable task;

    public HydroThread(H2Oa h2o) {
        this.task = h2o::hydro;
    }

    public HydroThread(H2Ob h2o) {
        this.task = h2o::hydro;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(100));
            task.run();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class OxyThread extends Thread {
    private final Runnable task;

    public OxyThread(H2Oa h2o) {
        this.task = h2o::oxy;
    }

    public OxyThread(H2Ob h2o) {
        this.task = h2o::oxy;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(100));
            task.run();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
