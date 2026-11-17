import java.util.LinkedList;
import java.util.List;

public class FCFSScheduler extends Scheduler {
    @Override
    public List<String> schedule(List<Task> taskQueue) {
        List<String> results = new LinkedList<>();
        long start = System.currentTimeMillis();
        for (Task t: taskQueue) {
            int currentTime = (int) (System.currentTimeMillis() - start);
            try {
                this.cpu.execute(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String info = currentTime + " : " + t.toString();
            results.add(info);
        }
        return results;
    }
    
}
