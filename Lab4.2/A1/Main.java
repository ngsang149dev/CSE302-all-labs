package A1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int MOLECULES = 100;
        runA(MOLECULES);
        runB(MOLECULES);
    }

    private static void runA(int molecules) throws InterruptedException {
        H2Oa h2o = new H2Oa();
        List<HydroThread> hydroThreads = new ArrayList<>();
        List<OxyThread> oxyThreads = new ArrayList<>();

        for (int i = 0; i < molecules; i++) {
            hydroThreads.add(new HydroThread(h2o));
            hydroThreads.add(new HydroThread(h2o));
            oxyThreads.add(new OxyThread(h2o));
        }

        for (Thread t : hydroThreads) {
            t.start();
        }

        for (Thread t : oxyThreads) {
            t.start();
        }

        for (Thread t : hydroThreads) {
            t.join();
        }

        for (Thread t : oxyThreads) {
            t.join();
        }

        System.out.println("Quest a: Done");
    }

    private static void runB(int molecules) throws InterruptedException {
        H2Ob h2o = new H2Ob();
        List<HydroThread> hydroThreads = new ArrayList<>();
        List<OxyThread> oxyThreads = new ArrayList<>();

        for (int i = 0; i < molecules; i++) {
            hydroThreads.add(new HydroThread(h2o));
            hydroThreads.add(new HydroThread(h2o));
            oxyThreads.add(new OxyThread(h2o));
        }

        for (Thread t : hydroThreads) {
            t.start();
        }

        for (Thread t : oxyThreads) {
            t.start();
        }

        for (Thread t : hydroThreads) {
            t.join();
        }

        for (Thread t : oxyThreads) {
            t.join();
        }

        System.out.println("Quest b: Done");
    }
}
