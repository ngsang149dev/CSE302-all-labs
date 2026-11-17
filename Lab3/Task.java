/**
 * Task
 */
public class Task {
    private String name;
    private int priority;
    private int burst;
    private int duration;
    private int remain;

    public Task(String name, int priority, int burst) {
        this.name = name;
        this.priority = priority;
        this.burst = burst;
        this.remain = burst;
    }

    public void reset() {
        this.remain = this.burst;
        this.duration = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getBurst() {
        return burst;
    }

    public void setBurst(int brust) {
        this.burst = brust;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }

    @Override
    public String toString() {
        return name + ", Pri: " + priority + " - Burst: " + burst + " - Duration: " + duration
                + " - Remain: " + remain;
    }

}