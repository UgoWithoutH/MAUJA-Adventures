package com.mauja.maujaadventures.jeu;

import javafx.application.Platform;

import static java.lang.Thread.sleep;

public class Boucle extends Observable implements Runnable {
    private static final long DEFAULT_MILIS = 25;
    private long milis  = DEFAULT_MILIS;
    private int timer = 0;
    private boolean running = false;

    public static long getDefaultMilis() {return DEFAULT_MILIS;}

    public long getMilis(){return milis;}
    public void setMilis(long milis){this.milis = milis;}

    public boolean isRunning(){return running;}
    public void setRunning(boolean run){running = run;}


    public void start() {
        running = true;
        run();
    }

    @Override
    public void run() {
        while(isRunning()) {
            try {
                sleep(milis);
                timer++;
                beep(timer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void beep(int timer) {
        Platform.runLater(() -> notifier(timer));
    }
}
