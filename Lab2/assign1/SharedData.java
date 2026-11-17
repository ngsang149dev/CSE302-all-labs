package assign1;

public class SharedData {
    private int data;

    public SharedData(int data) {
        this.data = data;
    }
    
    public void increment() {
        data++;
    }

    public void decrement() {
        data--;
    }

    public int getData() {
        return data;
    }
}
