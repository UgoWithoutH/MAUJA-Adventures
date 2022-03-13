package com.mauja.maujaadventures.jeu;

import java.util.LinkedList;

public abstract class Observable {
    private LinkedList<Observateur> lesObservateurs;

    public Observable() {
        lesObservateurs = new LinkedList<>();
    }

    public void attacher(Observateur listener){
        lesObservateurs.add(listener);
    }

    public void detacher(Observateur listener){
        lesObservateurs.remove(listener);
    }

    public void notifier(long timer){
        for (Observateur observateur : lesObservateurs){
            observateur.miseAJour(timer);
        }
    }
}