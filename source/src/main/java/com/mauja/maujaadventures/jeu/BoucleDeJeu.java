package com.mauja.maujaadventures.jeu;

import javafx.application.Platform;

import static java.lang.Thread.sleep;

public class BoucleDeJeu extends Observable implements Runnable {
    public static final int FPS_CIBLE = 60;
    public final long tempsLancement;
    private static final long TEMPS_NANOSECONDE = 1000000000;
    private static final long TEMPS_MILLISECONDE = 1000000;
    private final long TEMPS_AVANT_NOTIFICATION;

    private boolean actif;
    private long tempsCourant;
    private long dernierTemps;
    private long tempsEcoule;
    private long tempsEcouleTotal;

    public BoucleDeJeu(long tempsAvantNotification) throws IllegalArgumentException {
        if (tempsAvantNotification <= 0) {
            throw new IllegalArgumentException("Le temps avant la notification doit être supérieur à 0.");
        }
        TEMPS_AVANT_NOTIFICATION = tempsAvantNotification;
        tempsLancement = System.nanoTime();
        tempsEcouleTotal = 0;
        actif = false;
    }

    public BoucleDeJeu() {
        this (TEMPS_NANOSECONDE / FPS_CIBLE);
    }

    public long getTempsEcouleTotal() {
        return tempsEcouleTotal;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public void reprendre(){
        synchronized (this) {
            notify();
        }
    }

    @Override
    public void run() {
        actif = true;
        dernierTemps = System.nanoTime();
        long tempsAttente;

        while(actif) {
            tempsCourant = System.nanoTime();
            tempsEcoule = tempsCourant - dernierTemps;
            if (tempsEcoule >= TEMPS_AVANT_NOTIFICATION) {
                ticker(tempsCourant);
                tempsEcouleTotal += tempsEcoule;
                dernierTemps = tempsCourant;
            }
            else {
                tempsAttente = TEMPS_AVANT_NOTIFICATION - tempsEcoule;
                try {
                    sleep(tempsAttente / TEMPS_MILLISECONDE, (int) (tempsAttente % TEMPS_MILLISECONDE));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(!actif){
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void ticker(long timer) {
        Platform.runLater(() -> notifier(timer));
    }
}
