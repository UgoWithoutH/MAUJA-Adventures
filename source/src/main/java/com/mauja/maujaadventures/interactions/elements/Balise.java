package com.mauja.maujaadventures.interactions.elements;

import com.mauja.maujaadventures.observateurs.ObservableElementInteractif;

public abstract class Balise extends ObservableElementInteractif {

    private Balise baliseParente;

    public abstract void ajouter(Balise balise);

    public Balise getBaliseParente() {
        return baliseParente;
    }

    public void setBaliseParente(Balise baliseParente) {
        this.baliseParente = baliseParente;
    }
}
