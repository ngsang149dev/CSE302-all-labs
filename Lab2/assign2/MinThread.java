package assign2;

import java.util.Arrays;

public class MinThread extends Thread {
    private final StatisticData data;

    public MinThread(StatisticData data) {
        this.data = data;
    }

    @Override 
    public void run() {
        data.setMin(findMin());
    }

    private int findMin() {
        int[] nums = data.getNums();
        Arrays.sort(nums);
        return nums[0];
    }
}
