package com.mauja.maujaadventures.interactions.evenements;

import com.mauja.maujaadventures.interactions.Action;
import com.mauja.maujaadventures.interactions.ElementInteractif;

public class Evenement {
    private ElementInteractif elementInteractif;
    private Action action;

    public Evenement(ElementInteractif elementInteractif, Action action) {
        this.elementInteractif = elementInteractif;
        this.action = action;
    }

    public ElementInteractif getElementInteractif() {
        return elementInteractif;
    }

    public Action getAction() {
        return action;
    }
}
