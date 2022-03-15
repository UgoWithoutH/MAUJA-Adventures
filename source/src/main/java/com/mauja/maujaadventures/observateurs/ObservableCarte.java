package com.mauja.maujaadventures.observateurs;

import com.mauja.maujaadventures.monde.Carte;

import java.util.LinkedList;

public abstract class ObservableCarte {
    private LinkedList<ObservateurCarte> lesObservateurCarte;

    public ObservableCarte() {
        lesObservateurCarte = new LinkedList<>();
    }

    public void attacher(ObservateurCarte observateur){
        lesObservateurCarte.add(observateur);
    }

    public void detacher(ObservateurCarte observateur){
        lesObservateurCarte.remove(observateur);
    }

    public void notifier(Carte carte) {
        for (ObservateurCarte observateurEvenementiel : lesObservateurCarte){
            observateurEvenementiel.miseAJour(carte);
        }
    }
}