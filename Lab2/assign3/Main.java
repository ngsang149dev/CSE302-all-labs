package assign3;

import java.util.Scanner;

public class Main {
    private static final int NUMBER_OF_THREAD = 5;

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of points: ");

        long noPoint = sc.nextLong();
        if (noPoint <= 0) {
            System.out.println("The number of points must be greater than zero.");
            sc.close();
            return;
        }

        SharedPointData data = new SharedPointData(0);
        MonteCarloThread[] threads = new MonteCarloThread[NUMBER_OF_THREAD];
        long pointsPerThread = noPoint / NUMBER_OF_THREAD;

        for (int i = 0; i < NUMBER_OF_THREAD; i++) {
            threads[i] = new MonteCarloThread(pointsPerThread, data);
            threads[i].start();
        }

        for (MonteCarloThread thread : threads) {
            thread.join();
        }

        long insidePoints = data.getInsidePoints();
        double piEstimate = 4.0 * insidePoints / noPoint;
        System.out.println("Points inside the circle: " + insidePoints);
        System.out.println("Estimated value of pi: " + piEstimate);
        sc.close();
    }
}
