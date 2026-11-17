package assign1;

public class IncrementThread extends Thread {
    private final SharedData data;
    private final int times;

    public IncrementThread(SharedData data, int times) {
        this.data = data;
        this.times = times;
    }

    @Override 
    public void run() {
        int i = 0;
        while (i < times) {
            data.increment();
            i++;
        }
    }
}
