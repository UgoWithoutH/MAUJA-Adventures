package com.mauja.maujaadventures.observateurs;

import java.util.LinkedList;

public abstract class Observable {
    private LinkedList<Observateur> lesObservateurs;

    public Observable() {
        lesObservateurs = new LinkedList<>();
    }

    public void attacher(Observateur observateur){
        lesObservateurs.add(observateur);
    }

    public void detacher(Observateur observateur){
        lesObservateurs.remove(observateur);
    }

    public void notifier(long timer) {
        for (Observateur observateur : lesObservateurs) {
            observateur.miseAJour(timer);
        }
    }

    public boolean isAttache(Observateur observateur) {
        return lesObservateurs.contains(observateur);
    }
}