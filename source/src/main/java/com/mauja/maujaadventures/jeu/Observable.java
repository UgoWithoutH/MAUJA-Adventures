package com.mauja.maujaadventures.jeu;

import java.util.LinkedList;

public class Observable {
    private LinkedList<Observateur> lesObservateurs = new LinkedList<>();

    public void attacher(Observateur listener){
        lesObservateurs.add(listener);
    }

    public void detacher(Observateur listener){
        lesObservateurs.remove(listener);
    }

    public void notifier(int timer){
        for (Observateur observateur : lesObservateurs){
            observateur.miseAJour(timer);
        }
    }
}