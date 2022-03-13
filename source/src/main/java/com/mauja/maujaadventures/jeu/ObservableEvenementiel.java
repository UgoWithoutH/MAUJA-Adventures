package com.mauja.maujaadventures.jeu;

import com.mauja.maujaadventures.interactions.ElementInteractif;

import java.util.LinkedList;

public abstract class ObservableEvenementiel {
    private LinkedList<ObservateurEvenementiel> lesObservateurEvenementiels;

    public ObservableEvenementiel() {
        lesObservateurEvenementiels = new LinkedList<>();
    }

    public void attacher(ObservateurEvenementiel listener){
        lesObservateurEvenementiels.add(listener);
    }

    public void detacher(ObservateurEvenementiel listener){
        lesObservateurEvenementiels.remove(listener);
    }

    public void notifier(ElementInteractif elementInteractif, Boolean resultat, Object... parametres){
        for (ObservateurEvenementiel observateurEvenementiel : lesObservateurEvenementiels){
            observateurEvenementiel.miseAJour(elementInteractif, resultat, parametres);
        }
    }
}
