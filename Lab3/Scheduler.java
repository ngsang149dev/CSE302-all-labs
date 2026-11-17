import java.util.List;

public abstract class Scheduler {
    protected CPU cpu;

    public Scheduler() {
        this.cpu = new CPU();
    }

    public abstract List<String> schedule(List<Task> taskQueue);
}
