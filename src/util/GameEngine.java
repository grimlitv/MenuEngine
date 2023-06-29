package util;

import java.time.Duration;
import java.time.Instant;

public class GameEngine {

    public void setRunning(boolean running) {
        isRunning = running;
    }

    private volatile boolean isRunning;
    private FixedSizeGame window;

    public GameEngine(FixedSizeGame fixedSizeGame) {
        this.window = fixedSizeGame;
    }

    public void start() {
        isRunning = true;
        Thread thread = new Thread(this::run);
        thread.start();
    }

    public void run() {
        //TODO convert this to a System.nanoTime()
        Instant lastFrameTime = Instant.now();
        try {
            while(isRunning) {
                Instant time = Instant.now();
                double deltaTime = Duration.between(lastFrameTime, time).toNanos() * 10E-10;
                lastFrameTime = Instant.now();

                double deltaWanted = 0.0167;
                Globals.ph.update(deltaWanted);
                long msToSleep = (long)((deltaWanted - deltaTime) * 1000);
                if (msToSleep > 0) {
                    Thread.sleep(msToSleep);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        window.dispose();
    }

}
