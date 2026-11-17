package assign3;

public class SharedPointData {
    private long insidePoints;

    public SharedPointData(long insidePoints) {
        this.insidePoints = insidePoints;
        if (insidePoints < 0) {
            throw new IllegalArgumentException("insidePoints must not be negative");
        }
    }

    public synchronized long getInsidePoints() {
        return insidePoints;
    }

    public synchronized void addInsidePoints(long points) {
        if (points < 0) {
            throw new IllegalArgumentException("points must not be negative");
        }
        insidePoints+= points;
    }
}
