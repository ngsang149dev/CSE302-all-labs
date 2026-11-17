package A4;

import java.util.concurrent.ThreadLocalRandom;

public class WriterThread extends Thread{
    private SecondReaderWriter secondReaderWriter;

    public WriterThread(SecondReaderWriter secondReaderWriter) {
        this.secondReaderWriter = secondReaderWriter;
    }


    @Override 
    public void run() {
        try {
            secondReaderWriter.writerEnter();
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
        } catch (InterruptedException e) {
             e.printStackTrace();
        } finally {
            secondReaderWriter.writerExit();
        }
    }
}
