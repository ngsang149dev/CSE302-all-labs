package assign3;

import java.util.Random;

public class MonteCarloThread extends Thread {
    private final long points;
    private final SharedPointData sharedPointData;
    private Random random = new Random();

    public MonteCarloThread(long points, SharedPointData sharedPointData) {
        this.points = points;
        this.sharedPointData = sharedPointData;
    }

    public long getPoints() {
        return points;
    }

    @Override
    public void run() {
        long count = 0;
        for (long i = 0; i < points; i++) {
            double x = random.nextDouble(-1,1);
            double y = random.nextDouble(-1,1);
            if (x * x + y * y <= 1.0) {
                count++;
            }
        }
        sharedPointData.addInsidePoints(count);
    }
}
