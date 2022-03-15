package com.mauja.maujaadventures.interactions.elements;

public abstract class Balise {

    private Balise baliseParente;

    public abstract void ajouter(Balise balise);

    public Balise getBaliseParente() {
        return baliseParente;
    }

    public void setBaliseParente(Balise baliseParente) {
        this.baliseParente = baliseParente;
    }
}
