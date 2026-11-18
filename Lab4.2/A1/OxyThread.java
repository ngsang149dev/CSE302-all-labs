package A1;

import java.util.concurrent.ThreadLocalRandom;

public class OxyThread extends Thread {
    private H2Oa h2oA;
    private H2Ob h2oB;

    public OxyThread(H2Oa h2oA) {
        this.h2oA = h2oA;
    }

    public OxyThread(H2Ob h2oB) {
        this.h2oB = h2oB;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(100));

            if (h2oA != null) {
                h2oA.oxy();
            } else {
                h2oB.oxy();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
