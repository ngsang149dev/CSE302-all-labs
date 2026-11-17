package assign2;

public class AverageThread extends Thread {
    private final StatisticData data;

    public AverageThread(StatisticData data) {
        this.data = data;
    }
    
    @Override 
    public void run() {
        data.setAvg(calculateAvg());
    }

    private int calculateAvg() {
        int[] nums = data.getNums();
        long sum = 0;
        for (int i : nums) {
            sum+= i;
        }
        return (int) sum / nums.length;
    }
}
