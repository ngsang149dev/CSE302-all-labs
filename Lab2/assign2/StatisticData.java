package assign2;

public class StatisticData {
    private final int[] nums;
    private int avg; 
    private int max;
    private int min;
    private double median;

    public StatisticData(int[] nums) {
        this.nums = nums.clone();
    }

    public int[] getNums() {
        return nums.clone();
    }

    public int getAvg() {
        return avg;
    }

    public void setAvg(int avg) {
        this.avg = avg;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public double getMedian() {
        return median;
    }

    public void setMedian(double median) {
        this.median = median;
    }
    
}