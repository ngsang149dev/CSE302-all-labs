package A4;

import java.util.concurrent.ThreadLocalRandom;

public class ReaderThread extends Thread{
    private SecondReaderWriter secondReaderWriter;

    public ReaderThread(SecondReaderWriter secondReaderWriter) {
        this.secondReaderWriter = secondReaderWriter;
    }


    @Override 
    public void run() {
        try {
            secondReaderWriter.readerEnter();
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
        } catch (InterruptedException e) {
             e.printStackTrace();
        } finally {
            secondReaderWriter.readerExit();
        }
    }
}
