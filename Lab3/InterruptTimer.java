public class InterruptTimer {
    private CPU cpu;
    private TimerThread timerThread = null;

    public InterruptTimer(CPU cpu) {
        this.cpu = cpu;
    }

    public void start(int quantum) {
        if (this.timerThread != null) {
            this.stop();
        }
        this.timerThread = new TimerThread(this.cpu, quantum);
        this.timerThread.start();
    }
    public void stop() {
        if (this.timerThread != null) {
            this.timerThread.interrupt();
            try {
                this.timerThread.join();
            } catch (InterruptedException e) {
                // co the la timer vua ban interrupt vao chinh thread nay -> bo qua
            }
            this.timerThread = null;
        }
    }
}
