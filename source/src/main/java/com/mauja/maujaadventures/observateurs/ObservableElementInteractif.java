package com.mauja.maujaadventures.observateurs;

import com.mauja.maujaadventures.entites.Entite;
import com.mauja.maujaadventures.interactions.elements.ElementInteractif;

import java.util.LinkedList;

public class ObservableElementInteractif {
    private LinkedList<ObservateurElementInteractif> lesObservateurs;

    public ObservableElementInteractif() {
        lesObservateurs = new LinkedList<>();
    }

    public void attacher(ObservateurElementInteractif observateur){
        lesObservateurs.add(observateur);
    }

    public void detacher(ObservateurElementInteractif observateur){
        lesObservateurs.remove(observateur);
    }

    public void notifier(ElementInteractif elementInteractif) {
        for (ObservateurElementInteractif observateur : lesObservateurs) {
            observateur.miseAJour(elementInteractif);
        }
    }

    public boolean isAttache(ObservateurElementInteractif observateur) {
        return lesObservateurs.contains(observateur);
    }
}
