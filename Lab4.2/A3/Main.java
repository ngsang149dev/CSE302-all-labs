package A3;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        FirstReaderWriter firstReaderWriter = new FirstReaderWriter();
        List<ReaderThread> readerThreads = new ArrayList<>();
        List<WriterThread> writerThreads = new ArrayList<>();
        int readers = 10;
        int writers = 3;

        for (int i = 0; i< readers;i++) {
            readerThreads.add(new ReaderThread(firstReaderWriter));
        }

        for (int i = 0; i< writers;i++) {
            writerThreads.add(new WriterThread(firstReaderWriter));
        }

        for (Thread t : readerThreads) {
            t.start();
        }

        for (Thread t : writerThreads) {
            t.start();
        }

        for (Thread t : readerThreads) {
            t.join();
        }

        for (Thread t : writerThreads) {
            t.join();
        }

        System.out.println("Done");
    }
}
