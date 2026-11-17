public class CPU {
    private volatile Thread taskThread;

    public void execute(Task task) throws InterruptedException {
        Thread.interrupted();

        this.taskThread = Thread.currentThread();
        int remainBefore = task.getRemain();
        long startTime = System.currentTimeMillis();

        try {
            if (remainBefore > 0) {
                Thread.sleep(remainBefore);
            }

            task.setDuration(remainBefore);
            task.setRemain(0);
        } catch (InterruptedException e) {
            // Bi timer cat ngang (RR) hoac bi dung giua chung
            int duration = (int) (System.currentTimeMillis() - startTime);
            if (duration > remainBefore) duration = remainBefore;
            task.setDuration(duration);
            task.setRemain(remainBefore - duration);
            
            throw e;
        } finally {
            // Don taskThread TRUOC roi moi xoa co interrupt, de timer ban tre
            // se thay taskThread == null va khong danh vao quantum sau.
            this.taskThread = null;
            Thread.interrupted();
        }
    }

    public void interrupt() {
        Thread t = this.taskThread;
        if (t != null && t.isAlive()) {
            t.interrupt();
        }
    }
}
