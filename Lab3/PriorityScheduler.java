import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PriorityScheduler extends Scheduler {

    @Override
    public List<String> schedule(List<Task> taskQueue) {
        List<Task> sortedTasks = new ArrayList<>(taskQueue);
        sortedTasks.sort((s1, s2) -> Integer.compare(s1.getPriority(), s2.getPriority()));
        List<String> results = new LinkedList<>();
        long start = System.currentTimeMillis();
        for (Task t : sortedTasks) {
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