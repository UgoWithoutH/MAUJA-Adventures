package com.mauja.maujaadventures.interactions;

import java.util.ArrayList;
import java.util.List;

public abstract class Action extends Balise{
    protected List<ElementInteractif> listeElementInteractif;

    public Action() {
        listeElementInteractif =  new ArrayList<>();
    }

    public abstract void agit();

    @Override
    public void ajouter(Balise balise) {
        ajouterElementInteractif((ElementInteractif) balise);
    }

    private void ajouterElementInteractif(ElementInteractif elementInteractif){
        listeElementInteractif.add(elementInteractif);
    }
}
