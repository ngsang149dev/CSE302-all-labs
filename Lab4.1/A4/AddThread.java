package A4;

public class AddThread extends Thread {
    private final BoundedBuffer buffer;
    private final int[] values;

    public AddThread(BoundedBuffer buffer, int[] values) {
        this.buffer = buffer;
        this.values = values;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < values.length) {
            buffer.add(values[i]);
            i++;
        }
    }
}
