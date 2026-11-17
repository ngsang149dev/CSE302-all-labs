package assign2;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        if (args.length == 0) {
            System.out.println("Please input numbers");
            return;
        }
        int[] nums = new int[args.length];
        try {
            for (int i = 0; i< args.length;i++) {
                nums[i] = Integer.parseInt(args[i]);
            }
        } catch (NumberFormatException e) {
            System.out.println("Input has to be a string of integers");
            e.printStackTrace();
        }
        StatisticData data = new StatisticData(nums);

        AverageThread t1 = new AverageThread(data);
        t1.start();
        t1.join();
        System.out.println("Average number: " + data.getAvg());

        MinThread t2 = new MinThread(data);
        t2.start();
        t2.join();
        System.out.println("Min number: " + data.getMin());

        MaxThread t3 = new MaxThread(data);
        t3.start();
        t3.join();
        System.out.println("Max number: " + data.getMax());

        MedianThread t4 = new MedianThread(data);
        t4.start();
        t4.join();
        System.out.println("Median number: " + data.getMedian());

        
    }
}
