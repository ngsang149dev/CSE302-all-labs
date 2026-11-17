package assign2;

import java.util.Arrays;

public class MedianThread extends Thread {
    private final StatisticData data;

    public MedianThread(StatisticData data) {
        this.data = data;
    }

    @Override 
    public void run() {
        data.setMedian(calculateMedian());
    }

    private double calculateMedian() {
        double result = 0;
        int[] nums = data.getNums();
        Arrays.sort(nums);
        int length = nums.length;
        if (nums.length % 2 != 0) {
            result = nums[length/2];
        } else {
            result = (nums[length/2 - 1] + nums[length/2] / 2.0);
        }
        return result;
    }
}
