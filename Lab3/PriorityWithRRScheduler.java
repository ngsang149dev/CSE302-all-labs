import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class PriorityWithRRScheduler extends Scheduler {
    private static final int QUANTUM = 50;

    @Override
    public List<String> schedule(List<Task> taskQueue) {
        List<String> results = new LinkedList<>();

        TreeMap<Integer, Queue<Task>> tempQueue = new TreeMap<>();
        for (Task t : taskQueue) {
            tempQueue.computeIfAbsent(t.getPriority(), k -> new LinkedList<>()).add(t);
        }

        InterruptTimer timer = new InterruptTimer(this.cpu);
        long start = System.currentTimeMillis();

        while (!tempQueue.isEmpty()) {
            Map.Entry<Integer, Queue<Task>> entry = tempQueue.firstEntry();
            Queue<Task> group = entry.getValue();

            Task t = group.poll();
            if (group.isEmpty()) {
                tempQueue.remove(entry.getKey());
            }

            int currentTime = (int) (System.currentTimeMillis() - start);

            timer.start(QUANTUM);
            try {
                this.cpu.execute(t);      
            } catch (InterruptedException e) {
            } finally {
                timer.stop();
                Thread.interrupted();
            }

            results.add(currentTime + " : " + t.toString());

            if (t.getRemain() > 0) {
                tempQueue.computeIfAbsent(t.getPriority(), k -> new LinkedList<>()).add(t);
            }
        }
        return results;
    }

}
