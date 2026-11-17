package assign1;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int times = 1_000_000;
        SharedData data = new SharedData(0);
        IncrementThread t1 = new IncrementThread(data, times);
        DecrementThread t2 = new DecrementThread(data, times);

        System.out.println("Initial Value: " + data.getData());

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        
        System.out.println("After run, data: " + data.getData());
    }
}
