package assign2;

import java.util.Arrays;

public class MaxThread extends Thread {
    private final StatisticData data;

    public MaxThread(StatisticData data) {
        this.data = data;
    }

    @Override 
    public void run() {
        data.setMax(findMax());
    }

    private int findMax() {
        int[] nums = data.getNums();
        Arrays.sort(nums);
        return nums[nums.length-1];
    }
}
