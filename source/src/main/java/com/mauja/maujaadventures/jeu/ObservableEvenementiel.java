package com.mauja.maujaadventures.jeu;

import com.mauja.maujaadventures.interactions.elements.ElementInteractif;

import java.util.LinkedList;

public abstract class ObservableEvenementiel {
    private LinkedList<ObservateurEvenementiel> lesObservateurEvenementiels;

    public ObservableEvenementiel() {
        lesObservateurEvenementiels = new LinkedList<>();
    }

    public void attacher(ObservateurEvenementiel observateur){
        lesObservateurEvenementiels.add(observateur);
    }

    public void detacher(ObservateurEvenementiel observateur){
        lesObservateurEvenementiels.remove(observateur);
    }

    public void notifier(ElementInteractif elementInteractif, Boolean resultat, Object... parametres){
        for (ObservateurEvenementiel observateurEvenementiel : lesObservateurEvenementiels){
            observateurEvenementiel.miseAJour(elementInteractif, resultat, parametres);
        }
    }
}
