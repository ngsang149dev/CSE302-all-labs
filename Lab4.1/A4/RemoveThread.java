package A4;

public class RemoveThread extends Thread {
    private final BoundedBuffer buffer;
    private final int times;

    public RemoveThread(BoundedBuffer buffer, int times) {
        this.buffer = buffer;
        this.times = times;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < times) {
            buffer.remove();
            i++;
        }
    }
}
