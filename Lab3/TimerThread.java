public class TimerThread extends Thread {
    private CPU cpu;
    private int quantum;

    public TimerThread(CPU cpu, int quantum) {
        this.cpu = cpu;
        this.quantum = quantum;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(quantum);
            this.cpu.interrupt();
        } catch (InterruptedException e) {
            // Bi stop() huy truoc khi het quantum: chuyen binh thuong, khong phai loi
        }
        
    }
}
