import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<Task> taskQueue = new ArrayList<>();
    public static void main(String[] args) throws InterruptedIOException {
        
        try (BufferedReader reader = new BufferedReader(new FileReader("schedule.txt"))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 3) {
                    throw new IllegalArgumentException("Invalid input");
                }
                String name = parts[0].trim();
                int priority = Integer.parseInt(parts[1].trim());
                int burst = Integer.parseInt(parts[2].trim());

                Task task = new Task(name, priority, burst);
                taskQueue.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        } 
        System.out.println("Enter algorithm: ");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        switch (s) {
            case "FCFS" -> FCFSScheduler(); 
            case "SJF" -> SJFScheduler();
            case "Priority" -> PriorityScheduler();
            case "RR" -> RRScheduler();
            case "PriorityWithRR" -> PriorityWithRRScheduler();
        }    
        sc.close();    
    }

    private static void FCFSScheduler() {
        Scheduler scheduler = new FCFSScheduler();
        List<String> result = scheduler.schedule(taskQueue);
        System.out.println("Algorithm FCFS:");
        for (String rs : result) {
            System.out.println(rs);
        }

        for (Task t : taskQueue) {
            t.reset();
        }
    }

    private static void SJFScheduler() {
        Scheduler scheduler = new SJFScheduler();
        List<String> result = scheduler.schedule(taskQueue);
        System.out.println("Algorithm SJF:");
        for (String rs : result) {
            System.out.println(rs);
        }

        for (Task t : taskQueue) {
            t.reset();
        }
    }

    private static void PriorityScheduler() {
        Scheduler scheduler = new PriorityScheduler();
        List<String> result = scheduler.schedule(taskQueue);
        System.out.println("Algorithm Priority Scheduler:");
        for (String rs : result) {
            System.out.println(rs);
        }

        for (Task t : taskQueue) {
            t.reset();
        }
    }

    private static void RRScheduler() {
        Scheduler scheduler = new RRScheduler();
        List<String> result = scheduler.schedule(taskQueue);
        System.out.println("Algorithm RR:");
        for (String rs : result) {
            System.out.println(rs);
        }

        for (Task t : taskQueue) {
            t.reset();
        }
    }
    private static void PriorityWithRRScheduler() {
        Scheduler scheduler = new PriorityWithRRScheduler();
        List<String> result = scheduler.schedule(taskQueue);
        System.out.println("Algorithm Priority with RR:");
        for (String rs : result) {
            System.out.println(rs);
        }

        for (Task t : taskQueue) {
            t.reset();
        }
    }
}
