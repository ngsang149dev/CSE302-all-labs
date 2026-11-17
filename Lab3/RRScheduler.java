import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RRScheduler extends Scheduler {
    private static final int QUANTUM = 100;

    @Override
    public List<String> schedule(List<Task> taskQueue) {
        List<String> results = new LinkedList<>();
        Queue<Task> tempQueue = new LinkedList<>(taskQueue);
        InterruptTimer timer = new InterruptTimer(this.cpu);
        long start = System.currentTimeMillis();

        while (!tempQueue.isEmpty()) {
            Task t = tempQueue.poll();

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
                tempQueue.add(t);
            }
        }
        return results;
    }

}
