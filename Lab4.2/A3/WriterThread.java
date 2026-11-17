package A3;

import java.util.concurrent.ThreadLocalRandom;

public class WriterThread extends Thread{
    private FirstReaderWriter firstReaderWriter;

    public WriterThread(FirstReaderWriter firstReaderWriter) {
        this.firstReaderWriter = firstReaderWriter;
    }


    @Override 
    public void run() {
        try {
            firstReaderWriter.writerEnter();
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
        } catch (InterruptedException e) {
             e.printStackTrace();
        } finally {
            firstReaderWriter.writerExit();
        }
    }
}
