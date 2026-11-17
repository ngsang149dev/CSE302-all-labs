package A3;

import java.util.concurrent.ThreadLocalRandom;

public class ReaderThread extends Thread{
    private FirstReaderWriter firstReaderWriter;

    public ReaderThread(FirstReaderWriter firstReaderWriter) {
        this.firstReaderWriter = firstReaderWriter;
    }


    @Override 
    public void run() {
        try {
            firstReaderWriter.readerEnter();
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
        } catch (InterruptedException e) {
             e.printStackTrace();
        } finally {
            firstReaderWriter.readerExit();
        }
    }
}
